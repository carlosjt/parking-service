package py.com.parking.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.springframework.beans.factory.annotation.Autowired;
import py.com.parking.models.entities.ParkingPermissions;
import py.com.parking.models.repository.ParkingPermissionsRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ParkingPermissionsService {

    @Autowired
    ParkingPermissionsRepository parkingPermissionsRepository;

    public List<ParkingPermissions> findAll() {
        return parkingPermissionsRepository.findAll();
    }

    public Optional<ParkingPermissions> findById(Long id) {
        return parkingPermissionsRepository.findById(id);
    }

    public void deleteById(Long id) {
        parkingPermissionsRepository.deleteById(id);
    }
}
