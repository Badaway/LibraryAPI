package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.Dto.UpdateMemberDto;
import com.example.LibraryAPI.Dto.MemberResponseDto;
import com.example.LibraryAPI.model.Member;
import com.example.LibraryAPI.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("api/member")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final ModelMapper mapper;


    public MemberController(MemberService memberService, ModelMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @GetMapping("/members")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Object> getAllMembers() {

        var members= memberService.getAllMembers();
        var membersResponse=new ArrayList<MemberResponseDto>();
        for (Member i:members)
        {
            var member =mapper.map(i, MemberResponseDto.class);
            membersResponse.add(member);
        }
        return new ResponseEntity<>(membersResponse,HttpStatus.OK);
    }
    @GetMapping ("/{memberId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Object> getMember(@PathVariable int memberId){
        var member=mapper.map(memberService.getMember(memberId),MemberResponseDto.class);

        if(member ==null)
            return  new ResponseEntity<>("member does not exist", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(member, HttpStatus.OK);

    }

    @PutMapping("/{memberId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateMember(@PathVariable int memberId, @RequestBody UpdateMemberDto member){
        var currentMember = memberService.getMember(memberId);
        if(currentMember==null)
            return  ResponseEntity.badRequest().body("member does not exist");
        if ( member.getName()!=null )
            currentMember.setName(member.getName());

        var updatedMember=memberService.updateMember(currentMember);
        var memberResponse=mapper.map(updatedMember,MemberResponseDto.class);
        return new ResponseEntity<>(memberResponse,HttpStatus.OK);

    }

    @PostMapping ("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> CreateMember( @RequestBody Member member){



        var memberResponse=mapper.map(member,MemberResponseDto.class);
        return  new ResponseEntity<>(memberResponse, HttpStatus.CREATED);

    }

    @DeleteMapping ("/{memberId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> DeleteMember( @PathVariable int memberId){



        return memberService.deleteMember(memberId);

    }
}
