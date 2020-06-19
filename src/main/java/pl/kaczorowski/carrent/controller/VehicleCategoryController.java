package pl.kaczorowski.carrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kaczorowski.carrent.service.VehicleCategoryService;

import java.util.List;

@RestController
@RequestMapping("v1/category")
public class VehicleCategoryController {

    @Autowired
    private VehicleCategoryService vehicleCategoryService;

    @GetMapping
    public List<String> findAllNames() {
        return vehicleCategoryService.getCategories();
    }


}
