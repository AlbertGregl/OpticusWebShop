package gregl.opticuswebshop.service;

import gregl.opticuswebshop.DTO.model.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findAllCarts();
    Cart findCartById(Long id);
    Cart saveCart(Cart cart);
    void deleteCartById(Long id);
}
