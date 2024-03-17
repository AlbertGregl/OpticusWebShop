package gregl.opticuswebshop.controller;

import gregl.opticuswebshop.DTO.model.CartItems;
import gregl.opticuswebshop.DTO.model.Category;
import gregl.opticuswebshop.DTO.model.Eyewear;
import gregl.opticuswebshop.service.CategoryService;
import gregl.opticuswebshop.service.EyewearService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final EyewearService eyewearService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        List<Category> categories = categoryService.findAllCategories();
        List<Eyewear> eyewears = eyewearService.findAllEyewear();
        
        List<CartItems> cart = (List<CartItems>) session.getAttribute("cart");
        int cartItemCount = cart != null ? cart.stream().mapToInt(CartItems::getQuantity).sum() : 0;

        model.addAttribute("eyewears", eyewears);
        model.addAttribute("categories", categories);
        model.addAttribute("cartItemCount", cartItemCount);

        return "index";
    }
}
