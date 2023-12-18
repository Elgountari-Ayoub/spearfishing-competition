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
    Page<Ranking> findAll(Pageable pageable);
    Page<Ranking> findByCompetition(Competition competition, Pageable pageable);
    int countRankingByCompetition(Competition  competition);
    Page<Ranking> findByMember(Member member, Pageable pageable);
    Page<Ranking> findByCompetitionOrderByScoreDesc(Competition competition, Pageable pageable);
    List<Ranking> findByCompetitionOrderByScoreDesc(Competition competition);
    boolean existsByCompetitionCodeAndMemberNum(String competitionCode, long memberNum);

}
