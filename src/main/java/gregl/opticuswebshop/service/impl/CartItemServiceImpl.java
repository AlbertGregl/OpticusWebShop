package gregl.opticuswebshop.service.impl;

import gregl.opticuswebshop.DTO.model.CartItems;
import gregl.opticuswebshop.DTO.repository.CartItemRepository;
import gregl.opticuswebshop.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItems> findAllCartItem() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItems findCartItemById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public CartItems saveCartItem(CartItems cartItems) {
        return cartItemRepository.save(cartItems);
    }

    @Override
    public void deleteCartItemById(Long id) {
        cartItemRepository.deleteById(id);
    }
}
