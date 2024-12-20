package py.com.parking.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import py.com.parking.models.entities.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {
    List<Users> findAll();
    Optional<Users> findById(Integer id);
    Users save(Users user);
    void deleteById(Integer id) ;
}
