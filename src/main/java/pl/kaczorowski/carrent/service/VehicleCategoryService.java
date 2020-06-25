package pl.kaczorowski.carrent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczorowski.carrent.entity.VehicleCategory;
import pl.kaczorowski.carrent.exception.EntityNotFoundException;
import pl.kaczorowski.carrent.exception.ExceptionType;
import pl.kaczorowski.carrent.repository.VehicleCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleCategoryService {

    @Autowired
    private VehicleCategoryRepository vehicleCategoryRepository;

    public List<String> getCategories() {
        return vehicleCategoryRepository.findAll()
                .stream()
                .map(VehicleCategory::getName)
                .collect(Collectors.toList());
    }

    public VehicleCategory getCategory(Long id) {
        return vehicleCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ExceptionType.CATEGORY_NOT_FOUND, id.toString()));
    }

}
