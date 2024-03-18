package gregl.opticuswebshop.service.impl;

import gregl.opticuswebshop.DTO.model.PurchaseOrder;
import gregl.opticuswebshop.DTO.repository.PurchaseOrderRepository;
import gregl.opticuswebshop.service.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public List<PurchaseOrder> findAllPurchaseOrder() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public PurchaseOrder findPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id).orElse(null);
    }

    @Override
    public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public void deletePurchaseOrderById(Long id) {
        purchaseOrderRepository.deleteById(id);
    }

    @Override
    public List<PurchaseOrder> findPurchaseOrdersByUsername(String username) {
        return purchaseOrderRepository.findByUserUsername(username);
    }

    @Override
    public List<PurchaseOrder> findPurchaseOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return purchaseOrderRepository.findByPurchaseDateBetween(startDate, endDate);
    }
}
