package gregl.opticuswebshop.algebralab.service;

import gregl.opticuswebshop.algebralab.controller.model.Car;
import gregl.opticuswebshop.algebralab.dto.CarDTO;

import java.util.List;

public interface CarsService {
    List<CarDTO> GetAllCars();
    CarDTO getOneCar(int id);
    CarDTO addNewCar(CarDTO car);
}
