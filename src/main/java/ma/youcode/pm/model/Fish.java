package ma.youcode.pm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ma.youcode.pm.dto.HuntingDTO;
import ma.youcode.pm.dto.RankingDTO;

import java.util.Date;

@Data
@Entity
@Table(name = "fishes")
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = HuntingDTO.SaveValidationGroup.class)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private Double averageWeight;

    @ManyToOne
    @JoinColumn(name = "level_code")
    private Level level;
}
