package gregl.opticuswebshop.DTO.model;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;

@Entity
@Table(name = "cart")
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Timestamp createdAt;
}
