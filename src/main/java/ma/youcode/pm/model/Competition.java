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
    private String code;

    private Date date;
    private Date startTime;
    private Date endTime;
    private int numberOfParticipants;
    private String location;
    private Double amount;

    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;

    @ManyToMany
    @JoinTable(
            name = "rankings",
            joinColumns = @JoinColumn(name = "competition_code"),
            inverseJoinColumns = @JoinColumn(name = "member_num")
    )
    private List<Member> members;

}
