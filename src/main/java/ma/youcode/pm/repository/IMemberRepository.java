package ma.youcode.pm.repository;

import ma.youcode.pm.model.Hunting;
import ma.youcode.pm.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMemberRepository extends JpaRepository<Member, Long> {
    boolean existsByNum(Long num);
}
