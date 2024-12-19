package py.com.parking.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.parking.models.entities.ParkingPermissions;

import java.util.List;

public interface ParkingPermissionsRepository extends JpaRepository<ParkingPermissions, Long> {

    List<ParkingPermissions> findAll();
    ParkingPermissions save(ParkingPermissions parkingPermissions);
    void deleteById(Long id);
}
