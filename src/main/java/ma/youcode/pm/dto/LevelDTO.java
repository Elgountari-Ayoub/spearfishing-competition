package ma.youcode.pm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class LevelDTO {

    @NotNull(message = "code is required")
    private int code;

    @NotBlank(message = "Description is required")
    private String description;

    @Positive(message = "Points must be a non-negative integer")
    private int points;

}
