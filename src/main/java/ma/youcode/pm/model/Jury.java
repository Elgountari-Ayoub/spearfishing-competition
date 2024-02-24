package ma.youcode.pm.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ma.youcode.pm.enums.IdentityDocumentType;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
//@Table(name = "members")
public class Jury extends User{
}
