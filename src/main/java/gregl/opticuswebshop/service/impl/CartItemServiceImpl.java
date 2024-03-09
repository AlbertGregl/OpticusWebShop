package gregl.opticuswebshop.service.impl;

import gregl.opticuswebshop.DTO.model.CartItem;
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
    public List<CartItem> findAllCartItem() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem findCartItemById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItemById(Long id) {
        cartItemRepository.deleteById(id);
    }
}
