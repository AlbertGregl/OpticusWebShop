package gregl.opticuswebshop.controller;

import gregl.opticuswebshop.DTO.model.CartItems;
import gregl.opticuswebshop.DTO.model.Eyewear;
import gregl.opticuswebshop.DTO.model.PurchaseOrder;
import gregl.opticuswebshop.service.EyewearService;
import gregl.opticuswebshop.service.PurchaseOrderService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/complete-purchase")
    public String completePurchase(HttpSession session) {
        List<CartItems> cart = (List<CartItems>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return "redirect:/error";
        }

        PurchaseOrder order = new PurchaseOrder();
        // Set user, date, payment method, etc.

        order.setItems(new ArrayList<>(cart));
        purchaseOrderService.savePurchaseOrder(order);

        session.removeAttribute("cart");

        return "redirect:/order-confirmation";
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

}
