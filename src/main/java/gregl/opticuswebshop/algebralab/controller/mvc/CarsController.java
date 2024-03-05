package gregl.opticuswebshop.algebralab.controller.mvc;

import gregl.opticuswebshop.algebralab.controller.model.Color;
import gregl.opticuswebshop.algebralab.controller.model.EngineType;
import gregl.opticuswebshop.algebralab.dto.CarDTO;
import gregl.opticuswebshop.algebralab.service.CarsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/mvc/carswebshop")
@AllArgsConstructor
public class CarsController {

    private CarsService carsService;

    @GetMapping("/getAllCars.html")
    public String getAllCars(Model model) {
        model.addAttribute("cars", carsService.GetAllCars());
        return "cars";
    }

    @GetMapping("/getOneCar.html")
    public String getOneCar(@RequestAttribute Integer carId, Model model) {
        model.addAttribute("car", carsService.getOneCar(carId));
        return "cars";
    }

    @GetMapping("/saveNewCar.html")
    public String showScreenForNewCar(Model model) {
        model.addAttribute("colorsList", Color.values());
        model.addAttribute("engineTypes", EngineType.values());
        model.addAttribute("newCarDTO", new CarDTO());
        return "carsForm";
    }

    @PostMapping("/saveNewCar.html")
    public String saveNewCar(@ModelAttribute CarDTO carDTO, Model model) {
        model.addAttribute("carDTO", carDTO);
        carsService.addNewCar(carDTO);
        return "redirect:/mvc/carswebshop/getAllCars.html";
    }
}
