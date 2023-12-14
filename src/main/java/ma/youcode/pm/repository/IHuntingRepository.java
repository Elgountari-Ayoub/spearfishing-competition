package ma.youcode.pm.repository;

import ma.youcode.pm.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHuntingRepository extends JpaRepository<Hunting, Long> {
    boolean existsById(Long id);
    Hunting findByCompetitionAndMemberAndFish(Competition competition, Member member, Fish fish);
}
