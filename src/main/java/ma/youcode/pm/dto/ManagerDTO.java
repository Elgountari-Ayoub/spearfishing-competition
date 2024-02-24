package ma.youcode.pm.dto;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDTO extends UserDTO implements Serializable {
}
