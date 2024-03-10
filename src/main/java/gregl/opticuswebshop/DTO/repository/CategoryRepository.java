package gregl.opticuswebshop.DTO.repository;

import gregl.opticuswebshop.DTO.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
