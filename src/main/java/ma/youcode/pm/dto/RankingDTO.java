package ma.youcode.pm.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.pm.model.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankingDTO implements Serializable {
    @NotNull
    @Valid
    private RankingId id;

    @NotNull
    @Positive
    private int rank = 0;

    @NotNull
    @Positive
    private int score = 0;

    private List<Member> members;
    private List<Competition> competitions;
}
