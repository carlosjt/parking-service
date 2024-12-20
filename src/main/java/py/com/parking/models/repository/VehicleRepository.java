package py.com.parking.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import py.com.parking.models.entities.Vehicles;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicles, Integer> {
    List<Vehicles> findAll();
    Optional<Vehicles> findById(Integer id);
    void deleteById(Integer id);
    Optional<Vehicles> findVehiclesByLicensePlate(final String licensePlate);
    Optional<Vehicles> findUsersById(Integer userId);
}
