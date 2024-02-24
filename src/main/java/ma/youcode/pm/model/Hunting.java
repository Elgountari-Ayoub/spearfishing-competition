package ma.youcode.pm.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "huntings")
public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numberOfFish;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_code")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Competition competition;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fish_id")
    private Fish fish;

}
