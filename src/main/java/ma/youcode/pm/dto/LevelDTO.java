package ma.youcode.pm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class LevelDTO {

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Points is required")
    @PositiveOrZero(message = "Points must be a non-negative integer")
    private int points;

}
