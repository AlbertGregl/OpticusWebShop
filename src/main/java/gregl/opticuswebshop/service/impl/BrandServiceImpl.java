package gregl.opticuswebshop.service.impl;

import gregl.opticuswebshop.DTO.model.Brand;
import gregl.opticuswebshop.DTO.repository.BrandRepository;
import gregl.opticuswebshop.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    @Cacheable(value = "brands")
    public List<Brand> findAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findBrandById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    @Override
    public void saveBrand(String name) {
        Brand brand = new Brand();
        brand.setName(name);
        brandRepository.save(brand);
    }

    @Override
    public void deleteBrandById(Long id) {
        brandRepository.deleteById(id);
    }
}
