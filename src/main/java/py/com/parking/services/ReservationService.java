package py.com.parking.services;

import py.com.parking.models.entities.Reservation;
import py.com.parking.models.dto.ReservationDTO;
import py.com.parking.models.repository.ReservationRepository;
import py.com.parking.models.mappers.ReservationMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReservationService {

    @Inject
    ReservationRepository reservationRepository;

    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(ReservationMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ReservationDTO> getReservationById(Integer id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation != null ? Optional.of(ReservationMapper.INSTANCE.toDTO(reservation.get())) : Optional.empty();
    }

    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = ReservationMapper.INSTANCE.toEntity(reservationDTO);
        Reservation saved = reservationRepository.save(reservation);
        return ReservationMapper.INSTANCE.toDTO(saved);
    }

    public ReservationDTO updateReservation(Integer id, ReservationDTO reservationDTO) {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);
        if (existingReservation == null) {
            throw new IllegalArgumentException("Reservation not found.");
        }
        Reservation reservation = existingReservation.get();
        ReservationMapper.INSTANCE.mapDtoToEntity(reservationDTO,reservation);
        return ReservationMapper.INSTANCE.toDTO(reservationRepository.save(reservation));
    }

    public void deleteReservation(Integer id) {
        reservationRepository.deleteById(id);
    }
}
