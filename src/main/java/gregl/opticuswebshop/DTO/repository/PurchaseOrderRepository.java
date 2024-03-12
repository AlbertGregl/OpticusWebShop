package gregl.opticuswebshop.DTO.repository;

import gregl.opticuswebshop.DTO.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>{
}
