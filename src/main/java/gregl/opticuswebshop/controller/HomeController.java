package gregl.opticuswebshop.controller;

import gregl.opticuswebshop.DTO.model.Category;
import gregl.opticuswebshop.DTO.model.Eyewear;
import gregl.opticuswebshop.service.CategoryService;
import gregl.opticuswebshop.service.EyewearService;
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
    public String index(Model model) {
        List<Category> categories = categoryService.findAllCategories();
        List<Eyewear> eyewears = eyewearService.findAllEyewear();
        model.addAttribute("eyewears", eyewears);
        model.addAttribute("categories", categories);
        return "index";
    }
}
