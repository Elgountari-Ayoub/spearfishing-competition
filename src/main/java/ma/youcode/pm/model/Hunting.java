package ma.youcode.pm.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "huntings")
public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numberOfFish;

    @ManyToOne
    @JoinColumn(name = "member_num")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_code")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "fish_id")
    private Fish fish;

}
