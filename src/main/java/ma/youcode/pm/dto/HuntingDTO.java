package ma.youcode.pm.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import ma.youcode.pm.model.Competition;
import ma.youcode.pm.model.Fish;
import ma.youcode.pm.model.Member;

@Builder
@Data
public class HuntingDTO {
    @NotNull(message = "Id is required")
    private Long id;

    @Positive(message = "number of fish must be positive")
    private int numberOfFish = 1;

    @NotNull(message = "Member is required")
    private Member member;

    @NotNull(message = "Competition is required")
    private Competition competition;

    @NotNull(message = "Fish is required")
    private Fish fish;

}
