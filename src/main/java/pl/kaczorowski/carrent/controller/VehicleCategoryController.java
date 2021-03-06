package pl.kaczorowski.carrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kaczorowski.carrent.dto.VehicleCategoryDto;
import pl.kaczorowski.carrent.mapper.VehicleCategoryMapper;
import pl.kaczorowski.carrent.service.VehicleCategoryService;

import java.util.List;

@RestController
@RequestMapping("v2/category")
public class VehicleCategoryController {

    @Autowired
    private VehicleCategoryService vehicleCategoryService;

    @GetMapping
    public List<String> findAllNames() {
        return vehicleCategoryService.getCategories();
    }

    @GetMapping("/{id}")
    public VehicleCategoryDto getCategory(@PathVariable Long id) {
        return vehicleCategoryService.getCategory(id);
    }

}
