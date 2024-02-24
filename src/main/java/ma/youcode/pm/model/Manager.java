package ma.youcode.pm.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
//@Table(name = "managers")
public class Manager extends User{
}
