package gregl.opticuswebshop.service;

import gregl.opticuswebshop.DTO.model.Eyewear;

import java.util.List;

public interface EyewearService {
    List<Eyewear> findAllEyewear();
    Eyewear findEyewearById(Long id);
    void saveEyewear(Eyewear eyewear);
    void deleteEyewearById(Long id);
}
