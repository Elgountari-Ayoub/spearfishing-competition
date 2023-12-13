package ma.youcode.pm.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.pm.enums.IdentityDocumentType;
import ma.youcode.pm.model.Competition;
import ma.youcode.pm.model.Hunting;
import ma.youcode.pm.model.Ranking;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO implements Serializable {

//    @NotNull(message = "invalid num")
    private Long num;

    @NotBlank(message = "invalid name")
    private String name;

    @NotBlank(message = "invalid family name")
    private String familyName;

    @NotNull(message = "invalid accession date")
    private Date accessionDate;

    @NotBlank(message = "invalid nationality")
    private String nationality;

    @NotBlank(message = "invalid identity number")
    private String identityNumber;

    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;

    @AssertTrue(message = "invalid identityDocumentType")
    private boolean isValidIdentityDocument() {
        return identityDocument != null &&
                (identityDocument == IdentityDocumentType.CIN ||
                        identityDocument == IdentityDocumentType.CARTE_RESIDENCE ||
                        identityDocument == IdentityDocumentType.PASSPORT);
    }


    private List<Ranking> rankings;
    private List<Competition> competitions;
    private List<Hunting> huntings;
}
