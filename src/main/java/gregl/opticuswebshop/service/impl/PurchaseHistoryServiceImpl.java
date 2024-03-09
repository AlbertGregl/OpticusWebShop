package gregl.opticuswebshop.service.impl;

import gregl.opticuswebshop.DTO.model.PurchaseHistory;
import gregl.opticuswebshop.DTO.repository.PurchaseHistoryRepository;
import gregl.opticuswebshop.service.PurchaseHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {

    private final PurchaseHistoryRepository purchaseHistoryRepository;

    @Override
    public List<PurchaseHistory> findAllPurchaseHistory() {
        return purchaseHistoryRepository.findAll();
    }

    @Override
    public PurchaseHistory findPurchaseHistoryById(Long id) {
        return purchaseHistoryRepository.findById(id).orElse(null);
    }

    @Override
    public PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory) {
        return purchaseHistoryRepository.save(purchaseHistory);
    }

    @Override
    public void deletePurchaseHistoryById(Long id) {
        purchaseHistoryRepository.deleteById(id);
    }
}
