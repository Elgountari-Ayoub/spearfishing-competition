package ma.youcode.pm.controller;

import ma.youcode.pm.dto.member.MemberRequest;
import ma.youcode.pm.dto.member.MemberResponse;
import ma.youcode.pm.service.Implementation.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController extends ApiConfiguration {

    MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //ToDo Find All Members
    @GetMapping(value = {"/members"})
    public ResponseEntity<Page<MemberResponse>> findAllMembers(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<MemberResponse> members = memberService.finAll(pageable);
        return new ResponseEntity<>(members, HttpStatus.FOUND);
    }

    //ToDo Member Registration/Creation
    @PostMapping(value = "/members")
    public ResponseEntity<?> save(@RequestBody MemberRequest memberRequest) {
        return memberService.save(memberRequest);
    }

    //ToDo Update Member
    @PutMapping(value = "/members")
    public ResponseEntity<?> update(@RequestBody MemberRequest memberRequest) {
        return memberService.update(memberRequest);
    }

    //ToDo Delete Member
    @DeleteMapping(value = "/members")
    public ResponseEntity<?> delete(@RequestBody MemberRequest memberRequest){
        return memberService.delete(memberRequest.getNum());
    }


}
