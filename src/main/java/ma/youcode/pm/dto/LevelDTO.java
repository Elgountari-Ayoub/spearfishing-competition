package ma.youcode.pm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LevelDTO {
//    @NotNull(message = "Code is required")
    @NotNull(groups = SaveValidationGroup.class, message = "Code is required")
    private Long code;

    @NotBlank(message = "Description is required")
    private String description;

    @Positive(message = "Points must be a non-negative integer")
    private int points;

    public interface SaveValidationGroup{ }
}
