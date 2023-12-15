package ma.youcode.pm.dto;

import jakarta.validation.GroupSequence;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.pm.model.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

//@GroupSequence({RankingDTO.class, RankingDTO.SaveValidationGroup.class})
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankingDTO implements Serializable {
    @NotNull(groups = SaveValidationGroup.class)
    @Valid
    private RankingId id;

    @PositiveOrZero
    private int rank = 0;

    @PositiveOrZero
    private int score = 0;

    private Member member;
    private Competition competition;


    public interface SaveValidationGroup {}
}
