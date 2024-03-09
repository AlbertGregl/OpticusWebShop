package gregl.opticuswebshop.service;

import gregl.opticuswebshop.DTO.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    List<Manufacturer> findAllManufacturers();
    Manufacturer findManufacturerById(Long id);
    void saveManufacturer(Manufacturer manufacturer);
    void deleteManufacturerById(Long id);
}
