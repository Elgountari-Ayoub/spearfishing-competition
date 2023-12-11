package ma.youcode.pm.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;
import ma.youcode.pm.enums.IdentityDocumentType;
import ma.youcode.pm.model.Competition;
import ma.youcode.pm.model.Hunting;
import ma.youcode.pm.model.Ranking;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class MemberRequest {
    private String num;
    private String name;
    private String familyName;
    private Date accessionDate;
    private String nationality;
    private IdentityDocumentType identityDocument;
    private List<Ranking> rankings;
    private List<Competition> competitions;
    private List<Hunting> huntings;
}
