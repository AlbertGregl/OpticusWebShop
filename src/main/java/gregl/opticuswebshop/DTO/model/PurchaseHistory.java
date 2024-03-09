package gregl.opticuswebshop.DTO.model;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;

@Entity
@Table(name = "purchase_history")
@Data
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer purchaseId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @Column(nullable = false)
    private Timestamp purchaseDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;
}