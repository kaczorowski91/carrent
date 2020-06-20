package pl.kaczorowski.carrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.kaczorowski.carrent.entity.Assignment;
import pl.kaczorowski.carrent.entity.User;
import pl.kaczorowski.carrent.entity.Vehicle;
import pl.kaczorowski.carrent.entity.VehicleCategory;
import pl.kaczorowski.carrent.repository.AssignmentRepository;
import pl.kaczorowski.carrent.repository.UserRepository;
import pl.kaczorowski.carrent.repository.VehicleCategoryRepository;
import pl.kaczorowski.carrent.repository.VehicleRepository;

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


    @EventListener(ApplicationReadyEvent.class)
    public void addData() {
        System.out.println();
        User user1 = new User("Jack", "Sparrow", "05063200852");
        User user2 = new User("Indiana", "Jones", "62595352");
        User user3 = new User("Scarlett", "O'Hara", "050632133200852");
        User user4 = new User("Vito", "Corleone", "101");
        User user5 = new User("James", "Bond", "32131");
        User user6 = new User("Mia", "Wallece", "0506320083132152");
        User user7 = new User("Harry", "Potter", "5465");
        User user8 = new User("Natalie", "PortMan", "5435345");
        User user9 = new User("Will", "Smith", "3213213");
        User user10 = new User("Al", "Pacino", "312321345");
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
        Vehicle vehicle1 = new Vehicle("Seat", "00001", vehicleCategory1,200.02);
        Vehicle vehicle2 = new Vehicle("BMW", "00002", vehicleCategory2,23.94);
        Vehicle vehicle3 = new Vehicle("MERCEDES", "00003", vehicleCategory2,32.32);
        vehicleRepository.save(vehicle1);
        vehicleRepository.save(vehicle2);
        vehicleRepository.save(vehicle3);
        Assignment assignment1 = new Assignment(LocalDateTime.parse("2018-01-21T20:00"), LocalDateTime.parse("2019-03-21T08:00"),LocalDateTime.parse("2020-01-21T10:15"), user1,vehicle1);
        Assignment assignment2 = new Assignment(LocalDateTime.parse("2019-01-21T20:00"), LocalDateTime.parse("2019-04-21T10:00"),LocalDateTime.parse("2020-04-23T10:15"), user1,vehicle1);
        Assignment assignment3 = new Assignment(LocalDateTime.parse("2012-01-21T20:00"), LocalDateTime.parse("2020-03-21T20:00"),LocalDateTime.parse("2020-03-21T20:00"), user1,vehicle1);
        assignmentRepository.save(assignment1);
        assignmentRepository.save(assignment2);
        assignmentRepository.save(assignment3);
    }
}