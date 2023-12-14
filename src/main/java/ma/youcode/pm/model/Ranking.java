package ma.youcode.pm.model;


import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "rankings")
public class Ranking {
    @Id
    @EmbeddedId
    @Column(nullable = false)
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
