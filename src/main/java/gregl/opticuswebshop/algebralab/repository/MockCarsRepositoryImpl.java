package gregl.opticuswebshop.algebralab.repository;

import gregl.opticuswebshop.algebralab.controller.model.Car;
import gregl.opticuswebshop.algebralab.controller.model.Color;
import gregl.opticuswebshop.algebralab.controller.model.EngineType;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MockCarsRepositoryImpl implements CarsRepository{


    private static final List<Car> carList;

    static {
        carList = new ArrayList<>();
        Car newCar1 = new Car();
        Car newCar2 = new Car();
        Car newCar3 = new Car();

        newCar1.setId(1);
        newCar1.setManufacturer("Toyota");
        newCar1.setType("Corolla");
        newCar1.setEngineType(EngineType.FUEL);
        newCar1.setColor(Color.RED);
        newCar1.setManufacturingYear(2018);
        newCar1.setMileage(10000);
        newCar1.setPrice(new BigDecimal(15000));
        carList.add(newCar1);

        newCar2.setId(2);
        newCar2.setManufacturer("Audi");
        newCar2.setType("A4");
        newCar2.setEngineType(EngineType.FUEL);
        newCar2.setColor(Color.RED);
        newCar2.setManufacturingYear(2018);
        newCar2.setMileage(10000);
        newCar2.setPrice(new BigDecimal(15000));
        carList.add(newCar2);

        newCar3.setId(3);
        newCar3.setManufacturer("Subaru");
        newCar3.setType("Impreza");
        newCar3.setEngineType(EngineType.FUEL);
        newCar3.setColor(Color.RED);
        newCar3.setManufacturingYear(2018);
        newCar3.setMileage(10000);
        newCar3.setPrice(new BigDecimal(15000));
        carList.add(newCar3);

    }

    @Override
    public List<Car> getAllCars() {
        return carList;
    }

    @Override
    public Car getOneCar(int id) {
        return carList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Car addNewCar(Car car) {
        car.setId(generateNewCarId());
        carList.add(car);
        return car;
    }

    private Integer generateNewCarId(){
        return getAllCars()
                .stream()
                .max((c1, c2) -> c1.getId().compareTo(c2.getId()))
                .orElse(new Car())
                .getId() + 1;
    }
}
