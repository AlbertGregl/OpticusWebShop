package gregl.opticuswebshop.service;

import gregl.opticuswebshop.DTO.model.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAllBrands();
    Brand findBrandById(Long id);
    void saveBrand(String name);
    void deleteBrandById(Long id);
}
