package py.com.parking.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.springframework.beans.factory.annotation.Autowired;
import py.com.parking.models.entities.Vehicles;
import py.com.parking.models.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class VehiclesService {

    @Autowired
    VehicleRepository vehicleRepository;

    public List<Vehicles> findAll() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicles> findById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicles save(Vehicles vehicles) {
        return vehicleRepository.save(vehicles);
    }

    public void delete(Vehicles vehicles) {
        vehicleRepository.delete(vehicles);
    }
}
