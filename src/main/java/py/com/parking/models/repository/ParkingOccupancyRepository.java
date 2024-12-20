package py.com.parking.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import py.com.parking.models.entities.ParkingOccupancy;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingOccupancyRepository extends CrudRepository<ParkingOccupancy, Integer> {
    List<ParkingOccupancy> findAll();

    List<ParkingOccupancy> findByParkingAreaId(Integer parkingAreaId);

    @Query("SELECT p FROM ParkingOccupancy p WHERE p.vehicle.id = :vehicleId AND p.parkingArea.id = :parkingAreaId AND p.exitTime IS NULL ORDER BY p.entryTime DESC Limit 1")
    ParkingOccupancy findByVehicleIdAndParkingAreaIdAndExitTimeIsNull(
            @Param("vehicleId") Integer vehicleId,
            @Param("parkingAreaId") Integer parkingAreaId
    );
}
