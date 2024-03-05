package gregl.opticuswebshop.algebralab.repository;

import gregl.opticuswebshop.algebralab.controller.model.Car;

import java.util.List;

public interface CarsRepository {
    List<Car> getAllCars();
    Car getOneCar(int id);
    Car addNewCar(Car car);
}
