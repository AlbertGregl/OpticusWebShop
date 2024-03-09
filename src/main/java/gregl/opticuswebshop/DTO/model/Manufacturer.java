package gregl.opticuswebshop.DTO.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "manufacturers")
@Data
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer manufacturerId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
}
