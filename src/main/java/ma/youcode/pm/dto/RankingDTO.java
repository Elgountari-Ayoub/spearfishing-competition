package ma.youcode.pm.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.pm.model.*;

import java.io.Serializable;

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
