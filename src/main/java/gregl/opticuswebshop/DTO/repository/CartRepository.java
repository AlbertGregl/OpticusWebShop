package gregl.opticuswebshop.DTO.repository;

import gregl.opticuswebshop.DTO.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
}
