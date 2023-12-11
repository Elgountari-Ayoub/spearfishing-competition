package ma.youcode.pm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "fishes")
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double averageWeight;

    @ManyToOne
    @JoinColumn(name = "level_code")
    private Level level;
}
