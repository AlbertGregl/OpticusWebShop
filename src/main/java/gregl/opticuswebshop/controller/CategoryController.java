package gregl.opticuswebshop.controller;

import gregl.opticuswebshop.DTO.model.Category;
import gregl.opticuswebshop.DTO.util.FileUploadUtil;
import gregl.opticuswebshop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

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


    @PostMapping("/admin/editCategory.html")
    public String updateCategory(@RequestParam("categoryId") Integer id,
                                 @ModelAttribute("category") Category category,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes,
                                 @RequestParam("imgFile") MultipartFile imgFile) {
        if (!result.hasErrors()) {
            Category existingCategory = categoryService.findCategoryById(id.longValue());
            if (existingCategory != null) {
                existingCategory.setName(category.getName());
                existingCategory.setDescription(category.getDescription());

                if (!imgFile.isEmpty()) {
                    String imagePath = FileUploadUtil.saveFile(imgFile);
                    existingCategory.setImagePath(imagePath);
                }

                categoryService.saveCategory(existingCategory);
                redirectAttributes.addFlashAttribute("successMessage", "Category updated successfully!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Category not found!");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Validation errors occurred!");
        }
        return "redirect:/admin.html";
    }

}
