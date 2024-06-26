package gregl.opticuswebshop.DTO.repository;

import gregl.opticuswebshop.DTO.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>{
    List<PurchaseOrder> findByUserUsername(String username);
    List<PurchaseOrder> findByPurchaseDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
