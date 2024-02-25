package ma.youcode.pm.dto;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ManagerDTO extends UserDTO implements Serializable {
}
