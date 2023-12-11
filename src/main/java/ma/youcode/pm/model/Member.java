package ma.youcode.pm.model;

import jakarta.persistence.*;
import lombok.Data;
import ma.youcode.pm.enums.IdentityDocumentType;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Member {
    @Id
    private String num;
    private String name;
    private String familyName;
    private Date accessionDate;
    private String nationality;

    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;

    @OneToMany(mappedBy = "member")
    private List<Ranking> rankings;

    @ManyToMany(mappedBy = "members")
    private List<Competition> competitions;

    @OneToMany(mappedBy = "member")
    private List<Hunting> huntings;
}
