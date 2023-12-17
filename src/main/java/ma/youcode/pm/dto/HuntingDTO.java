package ma.youcode.pm.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.pm.model.Competition;
import ma.youcode.pm.model.Fish;
import ma.youcode.pm.model.Member;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HuntingDTO {
    private Long id;

    @Positive(message = "number of fish must be positive")
    private int numberOfFish;

    @NotNull(message = "Member is required")
    @Valid
    private Member member;

    @NotNull(message = "Competition is required")
    @Valid
    private Competition competition;

    @NotNull(message = "Fish is required")
    @Valid
    private Fish fish;
    public interface SaveValidationGroup {}



}
