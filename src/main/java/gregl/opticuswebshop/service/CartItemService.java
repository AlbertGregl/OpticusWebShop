package gregl.opticuswebshop.service;

import gregl.opticuswebshop.DTO.model.CartItem;

import java.util.List;

public interface CartItemService {
    List<CartItem> findAllCartItem();
    CartItem findCartItemById(Long id);
    CartItem saveCartItem(CartItem cartItem);
    void deleteCartItemById(Long id);
}
