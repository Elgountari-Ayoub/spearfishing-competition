package ma.youcode.pm.util;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CompetitionCodeGenerator {
    public String generate(String location, LocalDate date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        String formattedDate = date.format(dateFormatter);

        return location.substring(0, 3) + "-" + formattedDate.replace("-", "");
    }

}
