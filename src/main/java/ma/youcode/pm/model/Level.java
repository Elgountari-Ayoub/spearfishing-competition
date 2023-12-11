package ma.youcode.pm.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String description;
    private int points;

}
