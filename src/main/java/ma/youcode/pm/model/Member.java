package ma.youcode.pm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ma.youcode.pm.enums.IdentityDocumentType;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "members")
public class Member {
    @Id
    @NotBlank(message = "num must not be blank")
    @NotNull(message = "num must not be null")
    @NotEmpty(message = "num must not be empty")
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
