package ma.youcode.pm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.pm.model.Competition;
import ma.youcode.pm.model.Ranking;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionRankingsResponse implements Serializable {
    Competition competition;
    Page<Ranking> rankings;
}