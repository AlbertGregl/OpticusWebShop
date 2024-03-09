package gregl.opticuswebshop.service;

import gregl.opticuswebshop.DTO.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findUserById(Long id);
    void saveUser(User user);
    void deleteUserById(Long id);
}
