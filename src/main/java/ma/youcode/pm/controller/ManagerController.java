package ma.youcode.pm.controller;

import jakarta.validation.Valid;
import ma.youcode.pm.dto.MemberDTO;
import ma.youcode.pm.dto.MemberRankingsResponse;
import ma.youcode.pm.dto.UserDTO;
import ma.youcode.pm.repository.UserRepository;
import ma.youcode.pm.service.IManagerService;
import ma.youcode.pm.service.IMemberService;
import ma.youcode.pm.service.Implementation.ManagerService;
import ma.youcode.pm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manager")
@CrossOrigin(origins = "http://localhost:4200/")
public class ManagerController {

    @Autowired
    IManagerService managerService;

    @Autowired
    IMemberService memberService;
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Page<?>> findAll(Pageable pageable) {
        Page<UserDTO> users = userService.findUsers(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {

        UserDTO user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestBody UserDTO userDTO) {
        boolean isAccepted = managerService.validate(userDTO.getId());
        return ResponseEntity.status(HttpStatus.OK).body(isAccepted);
    }

    @PutMapping("/refuse")
    public ResponseEntity<Boolean> refuse(long id) {
        boolean isAccepted = managerService.refuse(id);
        return ResponseEntity.status(HttpStatus.OK).body(isAccepted);
    }
}
