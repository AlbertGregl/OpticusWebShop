package gregl.opticuswebshop.service;

import gregl.opticuswebshop.DTO.model.CartItems;

import java.util.List;

public interface CartItemService {
    List<CartItems> findAllCartItem();
    CartItems findCartItemById(Long id);
    CartItems saveCartItem(CartItems cartItems);
    void deleteCartItemById(Long id);
}
