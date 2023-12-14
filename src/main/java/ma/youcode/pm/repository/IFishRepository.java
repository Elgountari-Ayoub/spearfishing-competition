package ma.youcode.pm.repository;

import ma.youcode.pm.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFishRepository extends JpaRepository<Fish, Long> {
    Fish findByName(String name);
    boolean existsByName(String name);
    List<Fish> findByNameContainingIgnoreCase(String name);
}
