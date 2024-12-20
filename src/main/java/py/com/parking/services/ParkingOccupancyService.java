package py.com.parking.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.com.parking.models.dto.ParkingOccupancyDTO;
import py.com.parking.models.mappers.ParkingOccupancyMapper;
import py.com.parking.models.repository.ParkingOccupancyRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ParkingOccupancyService {

    @Inject
    ParkingOccupancyRepository parkingOccupancyRepository;

    public List<ParkingOccupancyDTO> getAllOccupancies() {
        return parkingOccupancyRepository.findAll().stream()
                .map(ParkingOccupancyMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public List<ParkingOccupancyDTO> getOccupanciesByParkingArea(Integer parkingAreaId) {
        return parkingOccupancyRepository.findByParkingAreaId(parkingAreaId).stream()
                .map(ParkingOccupancyMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}
