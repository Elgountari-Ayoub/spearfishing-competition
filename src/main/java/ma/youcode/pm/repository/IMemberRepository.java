package ma.youcode.pm.repository;

import ma.youcode.pm.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepository extends JpaRepository<Member, String> {
}
