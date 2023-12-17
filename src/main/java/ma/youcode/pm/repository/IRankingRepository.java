package ma.youcode.pm.repository;

import ma.youcode.pm.model.Competition;
import ma.youcode.pm.model.Member;
import ma.youcode.pm.model.Ranking;
import ma.youcode.pm.model.RankingId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRankingRepository extends JpaRepository<Ranking, RankingId> {
    boolean existsRankingByCompetitionAndMember(Competition competition, Member member);
//    List<Ranking> findAllOrderByScoreD
    Page<Ranking> findAllByOrderByScoreDesc(Pageable pageable);
    boolean existsByCompetitionCodeAndMemberNum(String competitionCode, long memberNum);
}
