package ma.youcode.pm.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class CompetitionDTO {
    private String code;
    private Date date;
    private Date startTime;
    private Date endTime;
    private int numberOfParticipants;
    private String location;
    private Double amount;
}
