package py.com.parking.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import py.com.parking.models.entities.ParkingArea;

import java.util.List;

@Repository
public interface ParkingAreaRepository extends CrudRepository<ParkingArea, Integer> {
    List<ParkingArea> findAll();
}
