package gregl.opticuswebshop.algebralab.dto;

import gregl.opticuswebshop.algebralab.controller.model.Color;
import gregl.opticuswebshop.algebralab.controller.model.EngineType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private String manufacturer;
    private String type;
    private EngineType engineType;
    private Color color;
    private Integer manufacturingYear;
    private Integer mileage;
    private BigDecimal price;
}
