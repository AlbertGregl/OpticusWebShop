package gregl.opticuswebshop.service;

import gregl.opticuswebshop.DTO.model.LoginHistory;

import java.util.List;

public interface LoginHistoryService {
    List<LoginHistory> findAllLoginHistory();
    LoginHistory findLoginHistoryById(Integer id);
    void saveLoginHistory(LoginHistory loginHistory);
    void deleteLoginHistoryById(Integer id);
}
