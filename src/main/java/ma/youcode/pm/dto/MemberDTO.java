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
    
    @NotBlank(message = "Wa La7ya Dir Chi 7aja, ra num hada")
    private String num;

    @NotBlank(message = "Wa La7ya Dir Chi 7aja, ra name hada")
    private String name;

    @NotBlank(message = "Wa La7ya Dir Chi 7aja, ra familyName hada")
    private String familyName;

    @NotNull(message = "Wa La7ya Dir Chi 7aja, ra accessionDate hada")
    private Date accessionDate;

    @NotBlank(message = "Wa La7ya Dir Chi 7aja, ra nationality haddi")
    private String nationality;

    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;

    @AssertTrue(message = "Wa La7ya Dir Chi 7aja, ra identityDocumentType hada")
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
