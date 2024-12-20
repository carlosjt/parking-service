package py.com.parking.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.com.parking.models.entities.ParkingPermissions;
import py.com.parking.models.repository.ParkingPermissionsRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ParkingPermissionsService {

    @Inject
    ParkingPermissionsRepository parkingPermissionsRepository;

    public List<ParkingPermissions> findAll() {
        return parkingPermissionsRepository.findAll();
    }

    public Optional<ParkingPermissions> findById(Integer id) {
        return parkingPermissionsRepository.findById(id);
    }

    public void deleteById(Integer id) {
        parkingPermissionsRepository.deleteById(id);
    }
}
