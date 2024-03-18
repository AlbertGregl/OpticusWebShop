package gregl.opticuswebshop.DTO.repository;

import gregl.opticuswebshop.DTO.model.Eyewear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EyewearRepository extends JpaRepository<Eyewear, Long> {

    List<Eyewear> findByStockQuantityGreaterThan(int quantity);
}
