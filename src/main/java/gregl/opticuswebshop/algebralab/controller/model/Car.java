package gregl.opticuswebshop.algebralab.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private Integer id;
    private String manufacturer;
    private String type;
    private EngineType engineType;
    private Color color;
    private Integer manufacturingYear;
    private Integer mileage;
    private BigDecimal price;
}
