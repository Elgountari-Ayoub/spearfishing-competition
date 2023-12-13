package ma.youcode.pm.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Embeddable
public class RankingId implements Serializable {
    private Long memberNum;
    private String competitionCode;
}
