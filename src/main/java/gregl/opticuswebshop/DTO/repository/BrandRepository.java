package gregl.opticuswebshop.DTO.repository;

import gregl.opticuswebshop.DTO.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{
}
