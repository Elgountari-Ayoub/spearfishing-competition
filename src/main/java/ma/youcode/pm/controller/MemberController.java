package ma.youcode.pm.controller;

import jakarta.validation.Valid;
import ma.youcode.pm.dto.MemberDTO;
import ma.youcode.pm.exception.DuplicateMemberException;
import ma.youcode.pm.exception.MemberNotFoundException;
import ma.youcode.pm.service.Implementation.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members/")
public class MemberController {

    MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;

    }

    //TODO:  Member Registration/Creation
    @PostMapping
    public ResponseEntity<MemberDTO> save(@Valid @RequestBody MemberDTO memberDTO) {
        MemberDTO createdMember = memberService.save(memberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    //TODO:  Find Member By Num
    @GetMapping("/{num}")
    public ResponseEntity<MemberDTO> findByNum(@PathVariable String num) {
            MemberDTO memberDTO = memberService.finByNum(num);
            return ResponseEntity.status(HttpStatus.FOUND).body(memberDTO);
    }

    //TODO:  Find All Members
    @GetMapping
    public ResponseEntity<Page<MemberDTO>> findAllMembers(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<MemberDTO> members = memberService.finAll(pageable);
        return ResponseEntity.status(HttpStatus.FOUND).body(members);
    }

    //TODO:  Update Member
    @PutMapping(value = "/{num}")
    public ResponseEntity<MemberDTO> update(@PathVariable String num, @Valid @RequestBody MemberDTO memberDTO) {
        MemberDTO updatedMember = memberService.update(num, memberDTO);
        return ResponseEntity.ok(updatedMember);

    }

    //TODO:  Delete Member
    @DeleteMapping(value = "/{num}")
    public ResponseEntity<MemberDTO> delete(@PathVariable String num) {
        memberService.delete(num);
        return ResponseEntity.noContent().build();


    }

}
