package gregl.opticuswebshop.controller;

import gregl.opticuswebshop.DTO.model.*;
import gregl.opticuswebshop.service.EyewearService;
import gregl.opticuswebshop.service.PayPalService;
import gregl.opticuswebshop.service.PurchaseOrderService;
import gregl.opticuswebshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final EyewearService eyewearService;
    private final PurchaseOrderService purchaseOrderService;
    private final UserService userService;
    private final PayPalService payPalService;

    @GetMapping("/getCart.html")
    public String showCart(Model model, HttpSession session) {

        List<CartItems> cart = (List<CartItems>) session.getAttribute("cart");

        double total = cart.stream().mapToDouble(item -> item.getQuantity() * item.getEyewear().getPrice()).sum();
        int itemCount = cart != null ? cart.stream().mapToInt(CartItems::getQuantity).sum() : 0;

        model.addAttribute("total", total);
        model.addAttribute("cart", cart);
        model.addAttribute("cartItemCount", itemCount);
        return "getCart";
    }

    @PostMapping("/add-to-cart/{eyewearId}")
    @ResponseBody
    public ResponseEntity<?> addToCart(@PathVariable Long eyewearId, HttpSession session, @RequestParam Integer quantity) {

        List<CartItems> cart = (List<CartItems>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        CartItems existingItem = cart.stream()
                .filter(item -> item.getEyewear().getEyewearId().equals(eyewearId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            Eyewear eyewear = eyewearService.findEyewearById(eyewearId);
            if (eyewear == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false, "message", "Eyewear not found"));
            }
            CartItems newItem = new CartItems();
            newItem.setEyewear(eyewear);
            newItem.setQuantity(quantity);
            cart.add(newItem);
        }

        session.setAttribute("cart", cart);
        int newCartItemCount = calculateCartItemCount(cart);
        return ResponseEntity.ok(Map.of("success", true, "cartItemCount", newCartItemCount));
    }


    @PostMapping("/remove-from-cart/{itemIndex}")
    @ResponseBody
    public ResponseEntity<?> removeFromCart(@PathVariable int itemIndex, HttpSession session) {
        List<CartItems> cart = (List<CartItems>) session.getAttribute("cart");
        if (cart != null && itemIndex >= 0 && itemIndex < cart.size()) {
            cart.remove(itemIndex);
            session.setAttribute("cart", cart);
            int newCartItemCount = calculateCartItemCount(cart);
            return ResponseEntity.ok(Map.of("success", true, "cartItemCount", newCartItemCount));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false, "message", "Invalid item index"));
        }
    }


    @PostMapping("/update-quantity/{eyewearId}")
    @ResponseBody
    public ResponseEntity<?> updateQuantity(@PathVariable Long eyewearId, @RequestParam Integer quantity, HttpSession session) {
        List<CartItems> cart = (List<CartItems>) session.getAttribute("cart");

        if (cart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false, "message", "Cart not found"));
        }

        CartItems itemToUpdate = cart.stream()
                .filter(item -> eyewearId.equals(item.getEyewear().getEyewearId()))
                .findFirst()
                .orElse(null);

        if (itemToUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false, "message", "Cart item not found"));
        }

        itemToUpdate.setQuantity(quantity);
        session.setAttribute("cart", cart);

        double newTotal = itemToUpdate.getQuantity() * itemToUpdate.getEyewear().getPrice();
        int newCartItemCount = calculateCartItemCount(cart);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("newTotal", newTotal);
        response.put("cartItemCount", newCartItemCount);

        return ResponseEntity.ok(response);
    }


    private int calculateCartItemCount(List<CartItems> cart) {
        return cart.stream().mapToInt(CartItems::getQuantity).sum();
    }


    @PostMapping("/completePurchase.html")
    public String completePurchase(
            HttpSession session,
            @RequestParam("paymentMethod") String paymentMethodStr,
            Authentication authentication,
            Model model) {


        List<CartItems> cart = (List<CartItems>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty() || authentication == null) {
            model.addAttribute("orderError", "There was an error with your order. Please try again.");
            return "getCart";
        }

        if ("PAYPAL".equalsIgnoreCase(paymentMethodStr)) {
            double total = cart.stream().mapToDouble(item -> item.getQuantity() * item.getEyewear().getPrice()).sum();
            String payPalOrderUrl = payPalService.createOrder(total, "USD");

            return "redirect:" + payPalOrderUrl;
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findUserByUsername(userDetails.getUsername());
        if (user == null) {
            model.addAttribute("orderError", "User not found. Please log in again.");
            return "getCart";
        }

        PurchaseOrder order = createOrderSkeleton(user, paymentMethodStr);

        finalizeOrder(order, cart);
        session.removeAttribute("cart");
        model.addAttribute("orderSuccess", "Thank you for your purchase! Your order number is " + order.getOrderId() + ".");
        model.addAttribute("cart", new ArrayList<CartItems>());
        return "getCart";
    }

    @GetMapping("/processPaypalOrder")
    public String processPaypalOrder(
            @RequestParam("token") String orderId,
            HttpSession session,
            Model model,
            Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findUserByUsername(userDetails.getUsername());
        if (user == null) {
            model.addAttribute("orderError", "User not found. Please log in again.");
            return "getCart";
        }

        List<CartItems> cart = (List<CartItems>) session.getAttribute("cart");

        PurchaseOrder order = createOrderSkeleton(user, "PAYPAL");

        finalizeOrder(order, cart);
        session.removeAttribute("cart");
        model.addAttribute("orderSuccess", "Thank you for your purchase! Your order number is " + orderId + ".");

        return "getCart";
    }

    @GetMapping("/cancelPaypalOrder")
    public String cancelPaypalOrder() {
        return "getCart";
    }

    private PurchaseOrder createOrderSkeleton(User user, String paymentMethodStr) {
        PurchaseOrder order = new PurchaseOrder();
        order.setUser(user);
        order.setPurchaseDate(LocalDateTime.now());
        order.setPaymentMethod(PaymentMethod.valueOf(paymentMethodStr.toUpperCase()));
        return order;
    }

    private void finalizeOrder(PurchaseOrder order, List<CartItems> cart) {
        for (CartItems item : cart) {
            item.setPurchaseOrder(order);
        }
        order.setItems(cart);
        purchaseOrderService.savePurchaseOrder(order);
        updateStockQuantities(cart);
    }

    private void updateStockQuantities(List<CartItems> cart) {
        for (CartItems item : cart) {
            Eyewear eyewear = item.getEyewear();
            eyewear.setStockQuantity(eyewear.getStockQuantity() - item.getQuantity());
            eyewearService.saveEyewear(eyewear);
        }
    }

}
