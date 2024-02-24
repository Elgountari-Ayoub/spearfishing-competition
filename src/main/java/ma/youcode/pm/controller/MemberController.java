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
@CrossOrigin(origins = "*")
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

    //TODO:  Find Member By Id
    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> findById(@PathVariable long id) {
            MemberDTO memberDTO = memberService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(memberDTO);
    }

    //TODO:  Find All Members
    @GetMapping
    public ResponseEntity<Page<MemberDTO>> findAll(Pageable pageable) {
        
        Page<MemberDTO> members = memberService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

    //TODO:  Find All Member Competitions
    @GetMapping("/{id}/competitions")
    public ResponseEntity<MemberRankingsResponse> findAllMemberCompetitions(@PathVariable long id, Pageable pageable) {
        MemberRankingsResponse memberRankingsResponse = memberService.findRankings(id, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(memberRankingsResponse);
    }

    //TODO:  Update Member
    @PutMapping(value = "/{id}")
    public ResponseEntity<MemberDTO> update(@PathVariable long id, @Valid @RequestBody MemberDTO memberDTO) {
        MemberDTO updatedMember = memberService.update(id, memberDTO);
        return ResponseEntity.ok(updatedMember);

    }

    //TODO:  Delete Member
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MemberDTO> delete(@PathVariable long id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
