package ma.youcode.pm.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ma.youcode.pm.dto.HuntingDTO;
import ma.youcode.pm.enums.IdentityDocumentType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "competitions")
public class Competition {
    @Id
    @Column(nullable = false)
    @NotNull
    private String code;

    private LocalDate date;

    @Temporal(TemporalType.TIME)
    private LocalTime startTime;

    @Temporal(TemporalType.TIME)
    private LocalTime endTime;
    private int numberOfParticipants;
    private String location;
    private Double amount;
}
