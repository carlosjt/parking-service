package py.com.parking.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import py.com.parking.models.entities.SpecialEvent;

import java.util.List;

@Repository
public interface SpecialEventRepository extends CrudRepository<SpecialEvent, Integer> {
    List<SpecialEvent> findAll();
}
