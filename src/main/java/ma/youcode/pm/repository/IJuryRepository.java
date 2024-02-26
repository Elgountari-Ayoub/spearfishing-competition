package ma.youcode.pm.repository;

import ma.youcode.pm.model.Jury;
import ma.youcode.pm.model.Manager;
import ma.youcode.pm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IJuryRepository extends JpaRepository<Jury, Long> {
    // Since email is unique, we'll find users by email
    Optional<User> findByEmail(String email);
//    List<User> findAllByStatus(UserStatus userStatus);
}
