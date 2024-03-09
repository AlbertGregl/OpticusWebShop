package gregl.opticuswebshop.DTO.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "brands")
@Data
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer brandId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
}
