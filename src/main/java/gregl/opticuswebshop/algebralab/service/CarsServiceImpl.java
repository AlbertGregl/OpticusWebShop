package gregl.opticuswebshop.algebralab.service;

import gregl.opticuswebshop.algebralab.controller.model.Car;
import gregl.opticuswebshop.algebralab.dto.CarDTO;
import gregl.opticuswebshop.algebralab.repository.CarsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarsServiceImpl implements CarsService{

    private CarsRepository carsRepository;

    @Override
    public List<CarDTO> GetAllCars() {
        List<Car> cars = carsRepository.getAllCars();
        return cars.stream().map(this::convertCarToDto).toList();
    }

    @Override
    public CarDTO getOneCar(int id) {
        Car car = carsRepository.getOneCar(id);
        return convertCarToDto(car);
    }

    @Override
    public CarDTO addNewCar(CarDTO car) {
        Car newCar = convertDtoToCar(car);
        newCar = carsRepository.addNewCar(newCar);
        return convertCarToDto(newCar);
    }

    private Car convertDtoToCar(CarDTO carDTO){
        return new Car(
                null,
                carDTO.getManufacturer(),
                carDTO.getType(),
                carDTO.getEngineType(),
                carDTO.getColor(),
                carDTO.getManufacturingYear(),
                carDTO.getMileage(),
                carDTO.getPrice()
        );
    }

    private CarDTO convertCarToDto(Car car){
        return new CarDTO(
                car.getManufacturer(),
                car.getType(),
                car.getEngineType(),
                car.getColor(),
                car.getManufacturingYear(),
                car.getMileage(),
                car.getPrice()
        );
    }
}
