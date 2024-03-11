package gregl.opticuswebshop.controller;

import gregl.opticuswebshop.DTO.model.Category;
import gregl.opticuswebshop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    private final CategoryService categoryService;

    @GetMapping("/admin.html")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public String adminDashboard(Model model) {
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        return "admin";
    }


}
