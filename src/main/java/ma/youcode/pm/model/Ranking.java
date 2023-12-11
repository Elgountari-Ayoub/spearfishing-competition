package ma.youcode.pm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Ranking {
    @Id
    @EmbeddedId
    private RankingId id;

    private int rank;
    private int score;

    @ManyToOne
    @MapsId("memberNum")
    @JoinColumn(name = "member_num", referencedColumnName = "num")
    private Member member;

    @ManyToOne
    @MapsId("competitionCode")
    @JoinColumn(name = "competition_code", referencedColumnName = "code")
    private Competition competition;


}
