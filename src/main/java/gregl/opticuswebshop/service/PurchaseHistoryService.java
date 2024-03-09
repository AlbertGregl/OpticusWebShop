package gregl.opticuswebshop.service;

import gregl.opticuswebshop.DTO.model.PurchaseHistory;

import java.util.List;

public interface PurchaseHistoryService {
    List<PurchaseHistory> findAllPurchaseHistory();
    PurchaseHistory findPurchaseHistoryById(Long id);
    PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory);
    void deletePurchaseHistoryById(Long id);
}
