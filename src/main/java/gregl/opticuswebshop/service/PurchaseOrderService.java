package gregl.opticuswebshop.service;

import gregl.opticuswebshop.DTO.model.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {
    List<PurchaseOrder> findAllPurchaseOrder();
    PurchaseOrder findPurchaseOrderById(Long id);
    PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder);
    void deletePurchaseOrderById(Long id);
}
