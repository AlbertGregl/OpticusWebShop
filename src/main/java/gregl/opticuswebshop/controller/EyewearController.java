package gregl.opticuswebshop.controller;

import gregl.opticuswebshop.DTO.model.Brand;
import gregl.opticuswebshop.DTO.model.Category;
import gregl.opticuswebshop.DTO.model.Eyewear;
import gregl.opticuswebshop.DTO.model.Manufacturer;
import gregl.opticuswebshop.DTO.util.FileUploadUtil;
import gregl.opticuswebshop.service.BrandService;
import gregl.opticuswebshop.service.CategoryService;
import gregl.opticuswebshop.service.EyewearService;
import gregl.opticuswebshop.service.ManufacturerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/eyewear")
public class EyewearController {

    private final EyewearService eyewearService;
    private final ManufacturerService manufacturerService;
    private final BrandService brandService;
    private final CategoryService categoryService;


    // TODO refactor this method, sometime ...
    @PostMapping("/admin/addEyewear.html")
    public String addEyewear(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("price") Double price,
                             @RequestParam("stockQuantity") Integer stockQuantity,
                             @RequestParam("manufacturerId") Long manufacturerId,
                             @RequestParam("brandId") Long brandId,
                             @RequestParam("categoryId") Long categoryId,
                             @RequestParam("image") MultipartFile imageFile,
                             RedirectAttributes redirectAttributes) {

        Manufacturer manufacturer = manufacturerService.findManufacturerById(manufacturerId);
        Brand brand = brandService.findBrandById(brandId);
        Category category = categoryService.findCategoryById(categoryId);

        Eyewear eyewear = new Eyewear();
        eyewear.setName(name);
        eyewear.setDescription(description);
        eyewear.setPrice(price);
        eyewear.setStockQuantity(stockQuantity);
        eyewear.setManufacturer(manufacturer);
        eyewear.setBrand(brand);
        eyewear.setCategory(category);

        if (!imageFile.isEmpty()) {
            String imagePath = FileUploadUtil.saveFile(imageFile);
            eyewear.setImagePath(imagePath);
        }

        eyewearService.saveEyewear(eyewear);
        redirectAttributes.addFlashAttribute("successMessage", "Eyewear added successfully!");
        return "redirect:/admin.html";
    }

    @PostMapping("/admin/deleteEyewear.html/{id}")
    public String deleteEyewear(@PathVariable("id") Long eyewearId, RedirectAttributes redirectAttributes) {
        eyewearService.deleteEyewearById(eyewearId);
        redirectAttributes.addFlashAttribute("successMessage", "Eyewear deleted successfully!");
        return "redirect:/admin.html";
    }

    // TODO refactor this method, sometime ...
    @PostMapping("/admin/editEyewear.html")
    public String updateEyewear(@RequestParam("eyewearId") Integer id,
                                @ModelAttribute("eyewear") Eyewear eyewear,
                                BindingResult result,
                                RedirectAttributes redirectAttributes,
                                @RequestParam("imgFile") MultipartFile imgFile,
                                @RequestParam("manufacturerId") Long manufacturerId,
                                @RequestParam("brandId") Long brandId,
                                @RequestParam("categoryId") Long categoryId) {

        if (!result.hasErrors()) {
            Eyewear existingEyewear = eyewearService.findEyewearById(id.longValue());
            if (existingEyewear != null) {
                existingEyewear.setName(eyewear.getName());
                existingEyewear.setDescription(eyewear.getDescription());
                existingEyewear.setPrice(eyewear.getPrice());
                existingEyewear.setStockQuantity(eyewear.getStockQuantity());

                Manufacturer manufacturer = manufacturerService.findManufacturerById(manufacturerId);
                Brand brand = brandService.findBrandById(brandId);
                Category category = categoryService.findCategoryById(categoryId);

                existingEyewear.setManufacturer(manufacturer);
                existingEyewear.setBrand(brand);
                existingEyewear.setCategory(category);

                if (!imgFile.isEmpty()) {
                    String imagePath = FileUploadUtil.saveFile(imgFile);
                    existingEyewear.setImagePath(imagePath);
                }

                eyewearService.saveEyewear(existingEyewear);
                redirectAttributes.addFlashAttribute("successMessage", "Eyewear updated successfully!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Eyewear not found!");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Validation errors occurred!");
        }
        return "redirect:/admin.html";
    }

}