package gregl.opticuswebshop.service.impl;

import gregl.opticuswebshop.DTO.model.Cart;
import gregl.opticuswebshop.DTO.repository.CartRepository;
import gregl.opticuswebshop.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    @Override
    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }
}
