package gregl.opticuswebshop.algebralab.controller.rest;

import gregl.opticuswebshop.algebralab.dto.CarDTO;
import gregl.opticuswebshop.algebralab.service.CarsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/carswebshop")
@AllArgsConstructor
public class RestCarsController {

    private CarsService carsService;

    @GetMapping("/cars")
    public List<CarDTO> getAllCars() {
        return carsService.GetAllCars();
    }

    @GetMapping("/car/{carId}")
    public CarDTO getOneCar(@PathVariable Integer carId) {
        return carsService.getOneCar(carId);
    }

    @PostMapping("/car")
    public CarDTO addNewCar(@RequestBody CarDTO carDTO) {
        return carsService.addNewCar(carDTO);
    }
}
