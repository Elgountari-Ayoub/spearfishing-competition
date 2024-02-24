package ma.youcode.pm.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ma.youcode.pm.dto.RankingDTO;

import java.io.Serializable;
import java.util.Date;

@Data
@Embeddable
public class RankingId implements Serializable {
    @NotNull(groups = RankingDTO.SaveValidationGroup.class)
    private Long memberId;

    @NotNull(groups = RankingDTO.SaveValidationGroup.class)
    private String competitionCode;
}
