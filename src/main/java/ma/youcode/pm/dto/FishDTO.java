package ma.youcode.pm.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import ma.youcode.pm.model.Level;

import java.io.Serializable;

@Data
public class FishDTO implements Serializable {

    @NotNull(message = "Code is required")
    private long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Positive(message = "Points must be a non-negative integer")
    private double averageWeight;

    @Valid
    @NotNull(message = "Level is required")
    private Level level;

}
