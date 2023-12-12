package ma.youcode.pm.model;


import jakarta.persistence.*;
import lombok.Data;
import ma.youcode.pm.enums.IdentityDocumentType;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "competitions")
public class Competition {
    @Id
    @Column(nullable = false)
    private String code;

    private Date date;

    @Temporal(TemporalType.TIME)
    private Date startTime;

    @Temporal(TemporalType.TIME)
    private Date endTime;
    private int numberOfParticipants;
    private String location;
    private Double amount;
}
