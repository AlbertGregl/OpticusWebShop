package gregl.opticuswebshop.DTO.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "eyewear")
public class Eyewear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eyewear_id")
    private Integer eyewearId;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(columnDefinition = "TEXT", name = "description")
    private String description;

    @Column(nullable = false, name = "price")
    private Double price;

    @Column(nullable = false, name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "image_path")
    private String imagePath;
}
