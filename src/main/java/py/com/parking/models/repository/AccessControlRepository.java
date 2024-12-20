package py.com.parking.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import py.com.parking.models.entities.AccessControl;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccessControlRepository extends CrudRepository<AccessControl, Integer> {
    Optional<AccessControl> findByVehicleId(Integer vehicleId);
    List<AccessControl> findAll();
    @Query("SELECT a FROM AccessControl a WHERE a.vehicle.id = :vehicleId AND a.exitTime IS NULL ORDER BY a.entryTime DESC Limit 1")
    Optional<AccessControl> findVehicleAndExitTime(@Param("vehicleId") Integer vehicleId);}
