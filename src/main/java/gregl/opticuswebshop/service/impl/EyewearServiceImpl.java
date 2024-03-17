package gregl.opticuswebshop.service.impl;

import gregl.opticuswebshop.DTO.model.Eyewear;
import gregl.opticuswebshop.DTO.repository.EyewearRepository;
import gregl.opticuswebshop.service.EyewearService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EyewearServiceImpl implements EyewearService {

    private final EyewearRepository eyewearRepository;

    @Override
    public List<Eyewear> findAllEyewear() {
        return eyewearRepository.findAll();
    }

    @Override
    public Eyewear findEyewearById(Long id) {
        return eyewearRepository.findById(id).orElse(null);
    }

    @Override
    public void saveEyewear(Eyewear eyewear) {
        eyewearRepository.save(eyewear);
    }

    @Override
    public void deleteEyewearById(Long id) {
        eyewearRepository.deleteById(id);
    }
}
