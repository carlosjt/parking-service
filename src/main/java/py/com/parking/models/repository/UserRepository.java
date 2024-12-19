package py.com.parking.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.parking.models.entities.Users;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findAll();
    Optional<Users> findById(Long id);
    Users save(Users user);
    void deleteById(Long id) ;
}