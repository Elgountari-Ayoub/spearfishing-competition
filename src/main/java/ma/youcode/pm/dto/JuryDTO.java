package ma.youcode.pm.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ma.youcode.pm.enums.IdentityDocumentType;
import ma.youcode.pm.model.Competition;
import ma.youcode.pm.model.Hunting;
import ma.youcode.pm.model.Ranking;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class JuryDTO extends UserDTO implements Serializable {
}
