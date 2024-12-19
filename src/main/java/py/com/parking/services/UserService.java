package py.com.parking.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import py.com.parking.models.entities.ParkingPermissions;
import py.com.parking.models.entities.Users;
import py.com.parking.models.repository.ParkingPermissionsRepository;
import py.com.parking.models.repository.UserRepository;
import py.com.parking.utils.Category;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ParkingPermissionsRepository parkingPermissionsRepository;

    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public Users saveUserWithVehicles(Users user) {
        userRepository.save(user);

        String userType = user.getUserType();
        Category category;
        try {
            category = Category.valueOf(userType.toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid user type: " + userType);
        }

        ParkingPermissions permission = new ParkingPermissions();
        permission.setUser(user);
        permission.setCategory(category);
        permission.setAccessHours(category.getAccessHours());
        permission.setCreatedAt(LocalDateTime.now());
        permission.setUpdatedAt(LocalDateTime.now());
        parkingPermissionsRepository.save(permission);

        return user;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}

