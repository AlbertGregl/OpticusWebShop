package gregl.opticuswebshop.controller;

import gregl.opticuswebshop.DTO.model.Brand;
import gregl.opticuswebshop.DTO.model.Category;
import gregl.opticuswebshop.DTO.model.Eyewear;
import gregl.opticuswebshop.DTO.model.Manufacturer;
import gregl.opticuswebshop.service.BrandService;
import gregl.opticuswebshop.service.CategoryService;
import gregl.opticuswebshop.service.EyewearService;
import gregl.opticuswebshop.service.ManufacturerService;
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
    private final ManufacturerService manufacturerService;
    private final BrandService brandService;
    private final EyewearService eyewearService;

    @GetMapping("/admin.html")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public String adminDashboard(Model model) {
        List<Category> categories = categoryService.findAllCategories();
        List<Manufacturer> manufacturers = manufacturerService.findAllManufacturers();
        List<Brand> brands = brandService.findAllBrands();
        List<Eyewear> eyewears = eyewearService.findAllEyewear();

        model.addAttribute("categories", categories);
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("brands", brands);
        model.addAttribute("eyewears", eyewears);
        return "admin";
    }


}
