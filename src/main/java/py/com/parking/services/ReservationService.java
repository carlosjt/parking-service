package py.com.parking.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import py.com.parking.models.dto.ReservationDTO;
import py.com.parking.models.entities.ParkingArea;
import py.com.parking.models.entities.Reservation;
import py.com.parking.models.entities.Users;
import py.com.parking.models.repository.ParkingAreaRepository;
import py.com.parking.models.repository.ReservationRepository;
import py.com.parking.models.repository.UserRepository;
import py.com.parking.models.mappers.ReservationMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ReservationService {

    @Inject
    private ReservationRepository reservationRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ParkingAreaRepository parkingAreaRepository;

    @Inject
    private ReservationMapper reservationMapper;

    public List<ReservationDTO> findAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.toDTOList(reservations);
    }
    @Transactional
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Optional<Users> user = userRepository.findById(reservationDTO.getUser().getId());
        Optional<ParkingArea> parkingArea = parkingAreaRepository.findById(reservationDTO.getParkingArea().getId());

        if (user.isEmpty() || parkingArea.isEmpty()) {
            throw new IllegalArgumentException("User or Parking Area not found");
        }

        if (isConflict(parkingArea.get(), reservationDTO.getReservationDate(), reservationDTO.getStartTime(), reservationDTO.getEndTime())) {
            throw new IllegalArgumentException("Time conflict with another reservation");
        }

        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        reservation.setUser(user.get());
        reservation.setParkingArea(parkingArea.get());
        reservation.setStatus("PENDING");
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setStartTime(reservationDTO.getStartTime());
        reservation.setEndTime(reservationDTO.getEndTime());
        reservation.setSpecialEvent(reservationDTO.isSpecialEvent());

        reservationRepository.save(reservation);
        return reservationMapper.toDTO(reservation);
    }
    @Transactional
    public void deleteReservation(Integer id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            reservationRepository.delete(reservation.get());
        } else {
            throw new IllegalArgumentException("Reservation not found");
        }
    }
    @Transactional
    public ReservationDTO updateReservation(Integer id, ReservationDTO reservationDTO) {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);
        if (existingReservation.isEmpty()) {
            throw new IllegalArgumentException("Reservation not found");
        }

        Reservation reservation = existingReservation.get();
        reservation.setStartTime(reservationDTO.getStartTime());
        reservation.setEndTime(reservationDTO.getEndTime());
        reservation.setSpecialEvent(reservationDTO.isSpecialEvent());
        reservation.setNotes(reservationDTO.getNotes());

        reservationRepository.save(reservation);
        return reservationMapper.toDTO(reservation);
    }

    private boolean isConflict(ParkingArea parkingArea, LocalDate date, LocalTime startTime, LocalTime endTime) {
        List<Reservation> reservations = reservationRepository.findByParkingAreaAndReservationDate(parkingArea, date);
        for (Reservation reservation : reservations) {
            if (startTime.isBefore(reservation.getEndTime()) && endTime.isAfter(reservation.getStartTime())) {
                return true;
            }
        }
        return false;
    }
}
