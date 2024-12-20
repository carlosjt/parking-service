package py.com.parking.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import py.com.parking.models.dto.ParkingReservationDTO;
import py.com.parking.models.entities.ParkingArea;
import py.com.parking.models.entities.ParkingReservation;
import py.com.parking.models.entities.SpecialEvent;
import py.com.parking.models.entities.Users;
import py.com.parking.models.mappers.ParkingReservationMapper;
import py.com.parking.models.repository.ParkingAreaRepository;
import py.com.parking.models.repository.ParkingReservationRepository;
import py.com.parking.models.repository.SpecialEventRepository;
import py.com.parking.models.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ParkingReservationService {
    @Inject
    ParkingAreaService parkingAreaService;

    @Inject
    ParkingReservationRepository parkingReservationRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    ParkingAreaRepository parkingAreaRepository;

    @Inject
    SpecialEventRepository specialEventRepository;

    public List<ParkingReservationDTO> getAllReservations() {
        return parkingReservationRepository.findAll().stream()
                .map(ParkingReservationMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    public ParkingReservationDTO createReservation(ParkingReservationDTO parkingReservationDTO) {
        Optional<Users> user = userRepository.findById(parkingReservationDTO.getUser().getId());
        Optional<ParkingArea> parkingArea = parkingAreaRepository.findById(parkingReservationDTO.getParkingArea().getId());
        Optional<SpecialEvent> specialEvent = Optional.empty();

        if (parkingReservationDTO.getIsSpecialEvent()) {
            specialEvent = specialEventRepository.findById(parkingReservationDTO.getSpecialEvent().getId());
        }
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        if (parkingArea.isEmpty()) {
            throw new IllegalArgumentException("Parking Area not found");
        }

        if (isConflict(parkingArea.get(), parkingReservationDTO.getReservationDate(), parkingReservationDTO.getStartTime(), parkingReservationDTO.getEndTime())) {
            throw new IllegalArgumentException("Time conflict with another reservation");
        }

        specialEvent.ifPresent(event -> validateReservationWithinEvent(
                parkingReservationDTO.getReservationDate(),
                parkingReservationDTO.getStartTime(),
                parkingReservationDTO.getEndTime(),
                event
        ));

        parkingAreaService.updateParkingAreaOccupancy(parkingArea.get().getId(), 1);

        final LocalDateTime current = LocalDateTime.now();

        ParkingReservation parkingReservation = new ParkingReservation();
        parkingReservation.setUser(user.get());
        parkingReservation.setParkingArea(parkingArea.get());
        specialEvent.ifPresent(parkingReservation::setSpecialEvent);
        parkingReservation.setReservationDate(parkingReservationDTO.getReservationDate());
        parkingReservation.setStartTime(parkingReservationDTO.getStartTime());
        parkingReservation.setEndTime(parkingReservationDTO.getEndTime());
        parkingReservation.setStatus(parkingReservationDTO.getStatus());
        parkingReservation.setIsSpecialEvent(parkingReservationDTO.getIsSpecialEvent());
        parkingReservation.setCreatedAt(current);
        parkingReservation.setUpdatedAt(current);
        parkingReservationRepository.save(parkingReservation);

        return ParkingReservationMapper.INSTANCE.toDTO(parkingReservation);
    }
    @Transactional
    public void deleteReservation(Integer id) {
        Optional<ParkingReservation> reservation = parkingReservationRepository.findById(id);
        if (reservation.isPresent()) {
            parkingReservationRepository.delete(reservation.get());
            parkingAreaService.updateParkingAreaOccupancy(reservation.get().getParkingArea().getId(), -1);
        } else {
            throw new IllegalArgumentException("Reservation not found");
        }
    }
    @Transactional
    public ParkingReservationDTO updateReservation(Integer id, ParkingReservationDTO parkingReservationDTO) {
        Optional<ParkingReservation> existingReservation = parkingReservationRepository.findById(id);
        Optional<SpecialEvent> specialEvent = Optional.empty();

        if (existingReservation.isEmpty()) {
            throw new IllegalArgumentException("Reservation not found");
        }

        if (parkingReservationDTO.getIsSpecialEvent()) {
            specialEvent = specialEventRepository.findById(parkingReservationDTO.getSpecialEvent().getId());
        }

        ParkingReservation reservation = existingReservation.get();
        ParkingReservationMapper.INSTANCE.mapDtoToEntity(parkingReservationDTO, reservation);

        if (isConflict(reservation.getParkingArea(), parkingReservationDTO.getReservationDate(), parkingReservationDTO.getStartTime(), parkingReservationDTO.getEndTime())) {
            throw new IllegalArgumentException("Time conflict with another reservation");
        }

        specialEvent.ifPresent(event -> validateReservationWithinEvent(
                parkingReservationDTO.getReservationDate(),
                parkingReservationDTO.getStartTime(),
                parkingReservationDTO.getEndTime(),
                event
        ));

        parkingReservationRepository.save(reservation);
        return ParkingReservationMapper.INSTANCE.toDTO(reservation);
    }
    public ParkingReservationDTO getReservationById(Integer id) {
        return parkingReservationRepository.findById(id)
                .map(ParkingReservationMapper.INSTANCE::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with ID: " + id));
    }

    private boolean isConflict(ParkingArea parkingArea, LocalDate date, LocalTime startTime, LocalTime endTime) {
        List<ParkingReservation> parkingReservations = parkingReservationRepository.findByParkingAreaAndReservationDate(parkingArea, date);
        for (ParkingReservation parkingReservation : parkingReservations) {
            if (startTime.isBefore(parkingReservation.getEndTime()) && endTime.isAfter(parkingReservation.getStartTime())) {
                return true;
            }
        }
        return false;
    }

    private void validateReservationWithinEvent(LocalDate reservationDate, LocalTime startTime, LocalTime endTime, SpecialEvent specialEvent) {
        if (!reservationDate.isEqual(specialEvent.getEventDate())) {
            throw new IllegalArgumentException("Reservation date must match the event date: " + specialEvent.getEventDate());
        }

        if (startTime.isBefore(specialEvent.getStartTime()) || endTime.isAfter(specialEvent.getEndTime())) {
            throw new IllegalArgumentException("Reservation time must fall within the event time range: "
                    + specialEvent.getStartTime() + " to " + specialEvent.getEndTime());
        }
    }
}
