package py.com.parking.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.parking.models.entities.Vehicles;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository <Vehicles, Long> {
    List<Vehicles> findAll();
    Vehicles save(Vehicles vehicle);
    Optional<Vehicles> findById(Long id);
    void deleteById(Long id);
}
