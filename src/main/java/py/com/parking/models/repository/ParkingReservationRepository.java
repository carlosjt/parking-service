package py.com.parking.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import py.com.parking.models.entities.ParkingArea;
import py.com.parking.models.entities.ParkingReservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingReservationRepository extends CrudRepository<ParkingReservation, Integer> {
     Optional<ParkingReservation> findById(Integer id);
     List<ParkingReservation> findAll();
     List<ParkingReservation> findByParkingAreaAndReservationDate(ParkingArea parkingArea, LocalDate reservationDate);

}
