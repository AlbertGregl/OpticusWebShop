package gregl.opticuswebshop.controller;

import gregl.opticuswebshop.DTO.model.Category;
import gregl.opticuswebshop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model) {
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        return "index";
    }
}
