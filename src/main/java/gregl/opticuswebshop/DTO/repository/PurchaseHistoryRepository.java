package gregl.opticuswebshop.DTO.repository;

import gregl.opticuswebshop.DTO.model.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long>{
}
