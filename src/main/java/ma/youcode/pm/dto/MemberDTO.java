package ma.youcode.pm.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.AssertTrue;
import lombok.*;
import ma.youcode.pm.enums.IdentityDocumentType;
import ma.youcode.pm.model.Competition;
import ma.youcode.pm.model.Hunting;
import ma.youcode.pm.model.Ranking;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO extends UserDTO implements Serializable {
    @NotBlank(message = "family Name is required")
    private String familyName;

    private LocalDate accessionDate = LocalDate.now();

    @NotBlank(message = "Nationality is required")
    private String nationality;

    @NotBlank(message = "Identity Number is required")
    private String identityNumber;

    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;

    @AssertTrue
    private boolean isAcceptedIdentityDocument() {
        return identityDocument != null &&
                (identityDocument == IdentityDocumentType.CIN ||
                        identityDocument == IdentityDocumentType.CARTE_RESIDENCE ||
                        identityDocument == IdentityDocumentType.PASSPORT);
    }

    private List<Ranking> rankings;
    private List<Competition> competitions;
    private List<Hunting> huntings;
}
