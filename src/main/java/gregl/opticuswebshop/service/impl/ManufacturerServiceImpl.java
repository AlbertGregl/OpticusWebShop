package gregl.opticuswebshop.service.impl;

import gregl.opticuswebshop.DTO.model.Manufacturer;
import gregl.opticuswebshop.DTO.repository.ManufacturerRepository;
import gregl.opticuswebshop.service.ManufacturerService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    @Override
    @Cacheable(value = "manufacturerCache")
    public List<Manufacturer> findAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer findManufacturerById(Long id) {
        return manufacturerRepository.findById(id).orElse(null);
    }

    @Override
    public void saveManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void deleteManufacturerById(Long id) {
        manufacturerRepository.deleteById(id);
    }
}
