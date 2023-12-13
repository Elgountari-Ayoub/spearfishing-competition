package ma.youcode.pm.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Embeddable
public class RankingId implements Serializable {
    @NotNull
    private Long memberNum;
    @NotNull
    private String competitionCode;
}
