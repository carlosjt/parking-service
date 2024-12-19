package py.com.parking.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import py.com.parking.models.entities.Reservation;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
     List<Reservation> findAll();
}
