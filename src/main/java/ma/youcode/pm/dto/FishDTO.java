package ma.youcode.pm.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.pm.model.Level;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FishDTO implements Serializable {

//    @NotNull(message = "id is required")
    private long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Positive(message = "Points must be a non-negative integer")
    private double averageWeight;

    @Valid
    @NotNull(message = "Level is required")
    private Level level;

}
