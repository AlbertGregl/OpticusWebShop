package gregl.opticuswebshop.service.impl;

import gregl.opticuswebshop.DTO.model.LoginHistory;
import gregl.opticuswebshop.DTO.repository.LoginHistoryRepository;
import gregl.opticuswebshop.service.LoginHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoginHistoryServiceImpl implements LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;

    @Override
    public List<LoginHistory> findAllLoginHistory() {
        return loginHistoryRepository.findAll();
    }

    @Override
    public LoginHistory findLoginHistoryById(Integer id) {
        return loginHistoryRepository.findById(id).orElse(null);
    }

    @Override
    public void saveLoginHistory(LoginHistory loginHistory) {
        loginHistoryRepository.save(loginHistory);
    }

    @Override
    public void deleteLoginHistoryById(Integer id) {
        loginHistoryRepository.deleteById(id);
    }
}
