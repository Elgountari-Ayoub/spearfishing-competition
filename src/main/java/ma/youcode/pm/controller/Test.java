package ma.youcode.pm.controller;

import ma.youcode.pm.model.Member;
import ma.youcode.pm.service.Implementation.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    MemberService memberService;
    Member member;

    @Autowired
    public Test(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = {"/"})
    public String sayHello(@RequestParam(name = "name", defaultValue = "Ayoub") String name) {
        member = memberService.finByNum("1");
        return String.format("Hello, %s!", member.getName());


    }
}
