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

    /**
     * Obtiene todas las áreas de estacionamiento con su disponibilidad actual.
     *
     * @return Lista de DTOs de áreas de estacionamiento.
     */
    public List<ParkingAreaDTO> getAllParkingAreasWithAvailability(final Boolean available, final Boolean occupied) {
        return parkingAreaRepository.findAll().stream()
                .filter(parkingArea -> filterByAvailability(parkingArea, available, occupied))
                .map(ParkingAreaMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Crea un nuevo área de estacionamiento.
     *
     * @param parkingAreaDTO Datos del área de estacionamiento.
     * @return DTO del área de estacionamiento creada.
     */
    @Transactional
    public ParkingAreaDTO createParkingArea(ParkingAreaDTO parkingAreaDTO) {
        ParkingArea parkingArea = ParkingAreaMapper.INSTANCE.toEntity(parkingAreaDTO);
        parkingArea.setCreatedAt(LocalDateTime.now());
        parkingArea.setUpdatedAt(LocalDateTime.now());
        parkingAreaRepository.save(parkingArea);
        return ParkingAreaMapper.INSTANCE.toDTO(parkingArea);
    }

    /**
     * Actualiza un área de estacionamiento existente.
     *
     * @param id             ID del área de estacionamiento.
     * @param parkingAreaDTO Datos actualizados del área de estacionamiento.
     * @return DTO del área de estacionamiento actualizada.
     */
    @Transactional
    public ParkingAreaDTO updateParkingArea(final Integer id, final ParkingAreaDTO parkingAreaDTO) {
        final Optional<ParkingArea> parkingAreaEntity = parkingAreaRepository.findById(id);
        if (parkingAreaEntity.isEmpty()) {
            throw new IllegalArgumentException("Parking area not found with ID: " + id);
        }
        final ParkingArea parkingArea = parkingAreaEntity.get();
        ParkingAreaMapper.INSTANCE.mapDtoToEntity(parkingAreaDTO, parkingArea);
        return ParkingAreaMapper.INSTANCE.toDTO(parkingAreaRepository.save(parkingArea));
    }

    /**
     * Actualiza la ocupación de un área de estacionamiento.
     *
     * @param id            ID del área de estacionamiento.
     * @param occupiedSpaces Espacios ocupados actualizados.
     * @return DTO actualizado del área de estacionamiento.
     */
    @Transactional
    public ParkingAreaDTO updateParkingAreaOccupancy(final Integer id, final Integer occupiedSpaces) {
        final Optional<ParkingArea> parkingAreaEntity = parkingAreaRepository.findById(id);
        if (parkingAreaEntity.isEmpty()) {
            throw new IllegalArgumentException("Parking area not found with ID: " + id);
        }

        final ParkingArea parkingArea = parkingAreaEntity.get();
        if (occupiedSpaces < 0 || occupiedSpaces > parkingArea.getTotalSpaces()) {
            throw new IllegalArgumentException("Occupied spaces must be between 0 and the total spaces.");
        }

        parkingArea.setOccupiedSpaces(occupiedSpaces);
        return ParkingAreaMapper.INSTANCE.toDTO(parkingAreaRepository.save(parkingArea));
    }

    /**
     * Elimina un área de estacionamiento por su ID.
     *
     * @param id ID del área de estacionamiento a eliminar.
     */
    @Transactional
    public void deleteParkingArea(Integer id) {
        Optional<ParkingArea> parkingArea = parkingAreaRepository.findById(id);
        if (parkingArea.isEmpty()) {
            throw new IllegalArgumentException("Parking area not found with ID: " + id);
        }
        parkingAreaRepository.delete(parkingArea.get());
    }

    /**
     * Aplica los filtros de disponibilidad u ocupación a un área de estacionamiento.
     *
     * @param parkingArea El área de estacionamiento a evaluar.
     * @param available   Si es true, filtra áreas con espacios disponibles.
     * @param occupied    Si es true, filtra áreas completamente ocupadas.
     * @return true si el área cumple con los filtros; de lo contrario, false.
     */
    private boolean filterByAvailability(final ParkingArea parkingArea, final Boolean available, final Boolean occupied) {
        if (Boolean.TRUE.equals(available)) {
            return parkingArea.getOccupiedSpaces() < parkingArea.getTotalSpaces();
        }
        if (Boolean.TRUE.equals(occupied)) {
            return parkingArea.getOccupiedSpaces().equals(parkingArea.getTotalSpaces());
        }
        return true; // Sin filtros aplicados
    }
}
