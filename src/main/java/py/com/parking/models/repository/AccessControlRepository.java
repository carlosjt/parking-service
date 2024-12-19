package py.com.parking.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import py.com.parking.models.entities.AccessControl;

@Repository
public interface AccessControlRepository extends CrudRepository<AccessControl, Integer> {}
