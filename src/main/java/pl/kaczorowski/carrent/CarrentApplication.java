package pl.kaczorowski.carrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.kaczorowski.carrent.client.currency.CurrencyClient;
import pl.kaczorowski.carrent.client.currency.CurrencyDto;
import pl.kaczorowski.carrent.client.vehiclesCategory.VehiclesCategoryClient;
import pl.kaczorowski.carrent.entity.Assignment;
import pl.kaczorowski.carrent.entity.User;
import pl.kaczorowski.carrent.entity.Vehicle;
import pl.kaczorowski.carrent.entity.VehicleCategory;
import pl.kaczorowski.carrent.repository.*;

import java.time.LocalDateTime;

@SpringBootApplication
public class CarrentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarrentApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleCategoryRepository vehicleCategoryRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyClient currencyClient;

    @Autowired
    private VehiclesCategoryClient vehiclesCategoryClient;


    @EventListener(ApplicationReadyEvent.class)
    public void addData() {
        System.out.println();
        User user1 = new User("Jack", "Sparrow", "54");
        User user2 = new User("Indiana", "Jones", "54");
        User user3 = new User("Scarlett", "O'Hara", "5275");
        User user4 = new User("Vito", "Corleone", "101");
        User user5 = new User("James", "Bond", "557");
        User user6 = new User("Mia", "Wallece", "54");
        User user7 = new User("Harry", "Potter", "575");
        User user8 = new User("Natalie", "PortMan", "54");
        User user9 = new User("Will", "Smith", "45");
        User user10 = new User("Al", "Pacino", "21");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
        userRepository.save(user8);
        userRepository.save(user9);
        userRepository.save(user10);
        VehicleCategory vehicleCategory1 = new VehicleCategory("Bike");
        VehicleCategory vehicleCategory2 = new VehicleCategory("Car");
        VehicleCategory vehicleCategory3 = new VehicleCategory("Bus");
        vehicleCategoryRepository.save(vehicleCategory1);
        vehicleCategoryRepository.save(vehicleCategory2);
        vehicleCategoryRepository.save(vehicleCategory3);
        Vehicle vehicle1 = new Vehicle("Seat", "00001", vehicleCategory1, 200.02);
        Vehicle vehicle2 = new Vehicle("BMW", "00002", vehicleCategory2, 23.94);
        Vehicle vehicle3 = new Vehicle("MERCEDES", "00003", vehicleCategory2, 32.32);
        vehicleRepository.save(vehicle1);
        vehicleRepository.save(vehicle2);
        vehicleRepository.save(vehicle3);
        Assignment assignment1 = new Assignment(LocalDateTime.parse("2018-01-21T20:00"), LocalDateTime.parse("2019-03-21T08:00"), LocalDateTime.parse("2020-01-21T10:15"), user1, vehicle1);
        Assignment assignment2 = new Assignment(LocalDateTime.parse("2019-01-21T20:00"), LocalDateTime.parse("2019-04-21T10:00"), null, user5, vehicle2);
        Assignment assignment3 = new Assignment(LocalDateTime.parse("2012-01-21T20:00"), null, null, user8, vehicle3);
        assignmentRepository.save(assignment1);
        assignmentRepository.save(assignment2);
        assignmentRepository.save(assignment3);

        for(int i=0;i<vehiclesCategoryClient.getCategory().getResults().size();i++){
            VehicleCategory vehicleCategory100 = new VehicleCategory(vehiclesCategoryClient.getCategory().getResults().get(i).toString());
            vehicleCategoryRepository.save(vehicleCategory100);
        }

    }
}