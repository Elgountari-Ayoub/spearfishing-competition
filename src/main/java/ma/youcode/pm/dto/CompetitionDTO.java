package ma.youcode.pm.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionDTO {
//    @NotBlank(message = "invalid code")
    private String code;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotNull
    @Min(3)
    @Max(30)
    private int numberOfParticipants = 3;

    @NotBlank
    @Size(min = 3, max = 255)
    private String location;

    @NotNull
    private Double amount;
}
