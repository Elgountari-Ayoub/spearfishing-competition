package ma.youcode.pm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.pm.model.Competition;
import ma.youcode.pm.model.Member;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberCompetitionsResponse implements Serializable {
    Member member;
    List<Competition> competitions;
}