package gregl.opticuswebshop.controller;

import gregl.opticuswebshop.DTO.model.Category;
import gregl.opticuswebshop.DTO.model.Eyewear;
import gregl.opticuswebshop.DTO.util.FileUploadUtil;
import gregl.opticuswebshop.service.CategoryService;
import gregl.opticuswebshop.service.EyewearService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final EyewearService eyewearService;

    @PostMapping("/admin/addCategory.html")
    public String addCategory(@ModelAttribute("category") Category category,
                              @RequestParam("image") MultipartFile imageFile,
                              RedirectAttributes redirectAttributes) {
        if (!imageFile.isEmpty()) {
            String imagePath = FileUploadUtil.saveFile(imageFile);
            category.setImagePath(imagePath);
        }
        categoryService.saveCategory(category);
        redirectAttributes.addFlashAttribute("successMessage", "Category added successfully!");
        return "redirect:/admin.html";
    }


    @PostMapping("/admin/deleteCategory.html/{id}")
    public String deleteCategory(@PathVariable("id") Long categoryId, RedirectAttributes redirectAttributes) {
        categoryService.deleteCategoryById(categoryId);
        redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully!");
        return "redirect:/admin.html";
    }

    @GetMapping("/{id}")
    public String showCategoryProducts(@PathVariable("id") Long categoryId, Model model) {
        // Fetch products based on categoryId
        //List<Eyewear> products = eyewearService.findEyewearById(categoryId);
        //model.addAttribute("products", products);
        // Assuming you have a view named 'category-products.html'
        return "category-products";
    }


}
