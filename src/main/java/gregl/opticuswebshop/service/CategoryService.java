package gregl.opticuswebshop.service;

import gregl.opticuswebshop.DTO.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
    Category findCategoryById(Long id);
    Category saveCategory(Category category);
    void deleteCategoryById(Long id);
}
