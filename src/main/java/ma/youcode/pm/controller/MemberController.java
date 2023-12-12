package ma.youcode.pm.controller;

import ma.youcode.pm.dto.MemberRequest;
import ma.youcode.pm.dto.MemberResponse;
import ma.youcode.pm.model.Member;
import ma.youcode.pm.service.Implementation.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
//@RequestMapping("/members")
public class MemberController extends ApiConfiguration {

    MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //ToDo Find All Members
    @GetMapping(value = {"members"})
    public ResponseEntity<Page<MemberResponse>> findAllMembers(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<MemberResponse> members = memberService.finAll(pageable);
        return new ResponseEntity<>(members, HttpStatus.FOUND);
    }

    //ToDo Member Registration/Creation
    @PostMapping(value = "members")
    public ResponseEntity save(@RequestBody MemberRequest memberRequest) {
        ResponseEntity memberResponse = memberService.save(memberRequest);
//        if (memberResponse == null) {
//            return new ResponseEntity<>("Failed to create member. Invalid request.", HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(memberResponse, HttpStatus.CREATED);
    }


    //ToDo Update Member
    @PutMapping(value = "members")
    public ResponseEntity update(@RequestBody MemberRequest memberRequest ) {
        return memberService.save(memberRequest);
    }
    //ToDo Delete Member


}
