package gregl.opticuswebshop.DTO.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "eyewear")
@Data
public class Eyewear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eyewearId;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EyewearType type;

    @Column(nullable = false)
    private Integer stockQuantity;
}
