package gregl.opticuswebshop.DTO.repository;

import gregl.opticuswebshop.DTO.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Long>{
}
