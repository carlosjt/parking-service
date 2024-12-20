package py.com.parking.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import py.com.parking.models.dto.ParkingAreaDTO;
import py.com.parking.models.entities.ParkingArea;
import py.com.parking.models.mappers.ParkingAreaMapper;
import py.com.parking.models.repository.ParkingAreaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ParkingAreaService {

    @Inject
    ParkingAreaRepository parkingAreaRepository;

    public List<ParkingAreaDTO> getAllParkingAreasWithAvailability(final Boolean available, final Boolean occupied) {
        return parkingAreaRepository.findAll().stream()
                .filter(parkingArea -> filterByAvailability(parkingArea, available, occupied))
                .map(ParkingAreaMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ParkingAreaDTO createParkingArea(ParkingAreaDTO parkingAreaDTO) {
        ParkingArea parkingArea = ParkingAreaMapper.INSTANCE.toEntity(parkingAreaDTO);
        parkingArea.setCreatedAt(LocalDateTime.now());
        parkingArea.setUpdatedAt(LocalDateTime.now());
        parkingAreaRepository.save(parkingArea);
        return ParkingAreaMapper.INSTANCE.toDTO(parkingArea);
    }

    @Transactional
    public ParkingAreaDTO updateParkingArea(final Integer id, final ParkingAreaDTO parkingAreaDTO) {
        final Optional<ParkingArea> parkingAreaEntity = getParkingAreaById(id);
        if (parkingAreaEntity.isEmpty()) {
            throw new IllegalArgumentException("Parking area not found with ID: " + id);
        }
        final ParkingArea parkingArea = parkingAreaEntity.get();
        ParkingAreaMapper.INSTANCE.mapDtoToEntity(parkingAreaDTO, parkingArea);
        return ParkingAreaMapper.INSTANCE.toDTO(parkingAreaRepository.save(parkingArea));
    }

    @Transactional
    public ParkingAreaDTO updateParkingAreaOccupancy(final Integer id, final Integer occupiedSpaces) {
        final Optional<ParkingArea> parkingAreaEntity = getParkingAreaById(id);
        if (parkingAreaEntity.isEmpty()) {
            throw new IllegalArgumentException("Parking area not found with ID: " + id);
        }

        final ParkingArea parkingArea = parkingAreaEntity.get();
        if (occupiedSpaces > parkingArea.getTotalSpaces()) {
            throw new IllegalArgumentException("Occupied spaces must be the total spaces.");
        }
        final Integer occupiedSpacesCurrent = parkingArea.getOccupiedSpaces();
        if (occupiedSpaces > 0) {
            parkingArea.setOccupiedSpaces(occupiedSpacesCurrent + occupiedSpaces);
        } else if (occupiedSpaces < 0) {
            if (occupiedSpacesCurrent > 0) {
                parkingArea.setOccupiedSpaces(occupiedSpacesCurrent + occupiedSpaces);
            } else {
                parkingArea.setOccupiedSpaces(0);
            }
        }

        return ParkingAreaMapper.INSTANCE.toDTO(parkingAreaRepository.save(parkingArea));
    }

    public Optional<ParkingArea> getParkingAreaById(final Integer id) {
        return parkingAreaRepository.findById(id);
    }

    @Transactional
    public void deleteParkingArea(Integer id) {
        Optional<ParkingArea> parkingArea = getParkingAreaById(id);
        if (parkingArea.isEmpty()) {
            throw new IllegalArgumentException("Parking area not found with ID: " + id);
        }
        parkingAreaRepository.delete(parkingArea.get());
    }

    private boolean filterByAvailability(final ParkingArea parkingArea, final Boolean available, final Boolean occupied) {
        if (Boolean.TRUE.equals(available)) {
            return parkingArea.getOccupiedSpaces() < parkingArea.getTotalSpaces();
        }
        if (Boolean.TRUE.equals(occupied)) {
            return parkingArea.getOccupiedSpaces().equals(parkingArea.getTotalSpaces());
        }
        return true;
    }
}
