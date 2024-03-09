package gregl.opticuswebshop.DTO.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart_items")
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "eyewear_id", nullable = false)
    private Eyewear eyewear;

    @Column(nullable = false)
    private Integer quantity;
}
