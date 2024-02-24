package ma.youcode.pm.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ma.youcode.pm.dto.HuntingDTO;
import ma.youcode.pm.enums.IdentityDocumentType;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "members")
public class Member extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @NotNull
    private Long id;

    private String familyName;
    private LocalDate accessionDate;
    private String nationality;

    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;
    @Column(name = "identity_number", unique = true, nullable = false)
    private String identityNumber;
}
