package py.com.parking.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import py.com.parking.models.entities.ParkingPermissions;

import java.util.List;

@Repository
public interface ParkingPermissionsRepository extends CrudRepository<ParkingPermissions, Integer> {

    List<ParkingPermissions> findAll();
    ParkingPermissions save(ParkingPermissions parkingPermissions);
    void deleteById(Integer id);
}
