package gregl.opticuswebshop.DTO.repository;

import gregl.opticuswebshop.DTO.model.Eyewear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EyewearRepository extends JpaRepository<Eyewear, Long> {
}
