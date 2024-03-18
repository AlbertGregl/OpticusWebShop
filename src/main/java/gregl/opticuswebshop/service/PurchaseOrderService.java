package gregl.opticuswebshop.service;

import gregl.opticuswebshop.DTO.model.PurchaseOrder;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseOrderService {
    List<PurchaseOrder> findAllPurchaseOrder();
    PurchaseOrder findPurchaseOrderById(Long id);
    PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder);
    void deletePurchaseOrderById(Long id);
    List<PurchaseOrder> findPurchaseOrdersByUsername(String username);
    List<PurchaseOrder> findPurchaseOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate);


}
