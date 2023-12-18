package ma.youcode.pm.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.pm.model.Ranking;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionDTO implements Serializable {
//    @NotBlank(message = "Code is required")
    @NotBlank(groups = UpdateValidationGroup.class, message = "Code is required")
    private String code;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @NotNull
    @Min(3)
    @Max(30)
    private int numberOfParticipants;

    @Size(min = 3, max = 255)
    private String location;

    @NotNull(message = "Amount is required")
    private Double amount;

    List<Ranking> rankings;

    public interface UpdateValidationGroup {}
}