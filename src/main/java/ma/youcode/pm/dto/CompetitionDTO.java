package ma.youcode.pm.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class CompetitionDTO {
    @NotBlank(message = "invalid code")
    private String code;

    @NotBlank(message = "invalid date")
    private Date date;

    @NotBlank(message = "invalid start time")
    private Date startTime;

    @NotBlank(message = "invalid end time")
    private Date endTime;

    @NotBlank(message = "invalid number of participants")
    private int numberOfParticipants;

    @NotBlank(message = "invalid location")
    private String location;

    @NotBlank(message = "invalid amount")
    private Double amount;
}
