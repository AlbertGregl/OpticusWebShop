package gregl.opticuswebshop.DTO.repository;

import gregl.opticuswebshop.DTO.model.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Integer> {
}
