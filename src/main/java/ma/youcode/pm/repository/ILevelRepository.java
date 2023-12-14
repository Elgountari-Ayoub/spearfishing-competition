package ma.youcode.pm.repository;

import ma.youcode.pm.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILevelRepository extends JpaRepository<Level, Long> {
}
