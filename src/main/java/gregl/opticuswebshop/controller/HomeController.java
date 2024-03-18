package gregl.opticuswebshop.controller;

import gregl.opticuswebshop.DTO.model.CartItems;
import gregl.opticuswebshop.DTO.model.Category;
import gregl.opticuswebshop.DTO.model.Eyewear;
import gregl.opticuswebshop.DTO.model.PurchaseOrder;
import gregl.opticuswebshop.service.CategoryService;
import gregl.opticuswebshop.service.EyewearService;
import gregl.opticuswebshop.service.PurchaseOrderService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final EyewearService eyewearService;
    private final PurchaseOrderService purchaseOrderService;

    @GetMapping("/")
    public String index(Model model, Authentication authentication, HttpSession session) {
        List<Category> categories = categoryService.findAllCategories();
        List<Eyewear> eyewears = eyewearService.findAllEyewearInStock();

        List<CartItems> cart = (List<CartItems>) session.getAttribute("cart");
        int cartItemCount = cart != null ? cart.stream().mapToInt(CartItems::getQuantity).sum() : 0;

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            List<PurchaseOrder> purchaseOrders = purchaseOrderService.findPurchaseOrdersByUsername(username);
            model.addAttribute("purchaseOrdersIndex", purchaseOrders);
        }

        model.addAttribute("eyewears", eyewears);
        model.addAttribute("categories", categories);
        model.addAttribute("cartItemCount", cartItemCount);

        return "index";
    }
}
