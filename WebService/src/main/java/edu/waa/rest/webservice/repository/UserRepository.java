package edu.waa.rest.webservice.repository;

;
import edu.waa.rest.webservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository("userRepository")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
