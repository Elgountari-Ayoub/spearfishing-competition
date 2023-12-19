package ma.youcode.pm.controller;

import jakarta.validation.Valid;
import ma.youcode.pm.dto.MemberDTO;
import ma.youcode.pm.dto.MemberRankingsResponse;
import ma.youcode.pm.service.Implementation.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@CrossOrigin(origins = "http://localhost:4200/")
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
    public ResponseEntity<MemberDTO> findByNum(@PathVariable long num) {
            MemberDTO memberDTO = memberService.finByNum(num);
            return ResponseEntity.status(HttpStatus.FOUND).body(memberDTO);
    }

    //TODO:  Find All Members
    @GetMapping
    public ResponseEntity<Page<MemberDTO>> findAll(Pageable pageable) {
        
        Page<MemberDTO> members = memberService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.FOUND).body(members);
    }



    //TODO:  Find All Member Competitions
    @GetMapping("/{num}/competitions")
    public ResponseEntity<MemberRankingsResponse> findAllMemberCompetitions(@PathVariable long num, Pageable pageable) {
        MemberRankingsResponse memberRankingsResponse = memberService.findRankings(num, pageable);
        return ResponseEntity.status(HttpStatus.FOUND).body(memberRankingsResponse);
    }

    //TODO:  Update Member
    @PutMapping(value = "/{num}")
    public ResponseEntity<MemberDTO> update(@PathVariable long num, @Valid @RequestBody MemberDTO memberDTO) {
        MemberDTO updatedMember = memberService.update(num, memberDTO);
        return ResponseEntity.ok(updatedMember);

    }

    //TODO:  Delete Member
    @DeleteMapping(value = "/{num}")
    public ResponseEntity<MemberDTO> delete(@PathVariable long num) {
        memberService.delete(num);
        return ResponseEntity.noContent().build();
    }

}
