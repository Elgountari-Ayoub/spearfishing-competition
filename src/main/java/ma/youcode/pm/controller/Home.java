package ma.youcode.pm.controller;

import jakarta.servlet.http.HttpServletRequest;
import ma.youcode.pm.config.AppConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping(value = {"/", ""})
    public String sayHello(HttpServletRequest request) {
        String responsBody = String.format("Welcome Mr.%s in the PM's community", request.getParameter("name"));
        return responsBody;
    }

}
