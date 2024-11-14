package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.Dto.BookResponseDto;
import com.example.LibraryAPI.Dto.MemberDto;
import com.example.LibraryAPI.Dto.MemberResponseDto;
import com.example.LibraryAPI.Dto.UpdateMemberDto;
import com.example.LibraryAPI.mapping.Mapper;
import com.example.LibraryAPI.model.Member;
import com.example.LibraryAPI.service.BookService;
import com.example.LibraryAPI.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@RequestMapping(MemberController.baseControllerUri)
@RestController
public class MemberController {

    public static final String baseControllerUri = "/member";

    public static final String getAllMembersUri = "/";
    public static final String getMemberByIdUri = "/id/{memberId}";
    public static final String updateMemberUri = "/{memberId}";
    public static final String createMemberUri = "/";
    public static final String deleteMemberUri = "/{memberId}";
    public static final String borrowBookUri = "/{memberId}/borrow/{bookId}";
    public static final String checkOutBookUri = "/{memberId}/checkOut/{bookId}";
    public static final String getMemberByEmailUri = "/{email}";


    @Autowired
    private  MemberService memberService;

    @Autowired
    private  BookService bookService;
    @Autowired
    private Mapper mapper;


    @GetMapping(getAllMembersUri)
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<List<MemberResponseDto>> getAllMembers() {

        var members= memberService.getAllMembers();
        var membersResponse =new ArrayList<MemberResponseDto>();

        for (Member member:members)
        {
            var memberResponseDto =mapper.map(member, MemberResponseDto.class);
            membersResponse.add(memberResponseDto);
        }




        return new ResponseEntity<>(membersResponse,HttpStatus.OK);
    }
    @GetMapping (getMemberByIdUri)
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable UUID memberId){

        var member=memberService.getMember(memberId);

        var memberResponse=mapper.map(member,MemberResponseDto.class);

        return new ResponseEntity<>(memberResponse, HttpStatus.OK);

    }

    @PutMapping(updateMemberUri)
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable UUID memberId,@Valid @RequestBody UpdateMemberDto member){

        var returnedMember=memberService.updateMember(member,memberId);

        var memberResponse=mapper.map(returnedMember,MemberResponseDto.class);

        return new ResponseEntity<>(memberResponse,HttpStatus.OK);

    }

    @PostMapping (createMemberUri)
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<MemberDto> createMember(@Valid @RequestBody MemberDto member){

        var returnedMember =memberService.createMember(member);

        var memberResponse=mapper.map(returnedMember,MemberDto.class);
        return  new ResponseEntity<>(memberResponse, HttpStatus.CREATED);

    }

    @DeleteMapping (deleteMemberUri)
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<String> deleteMember( @PathVariable UUID memberId){

        memberService.deleteMember(memberId);

        return ResponseEntity.noContent().build();

    }

    @PutMapping (borrowBookUri)
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<BookResponseDto> borrowBook( @PathVariable UUID memberId,@PathVariable UUID bookId){

        var book=bookService.borrowBook(memberId,bookId);

        var bookResponse=mapper.map(book,BookResponseDto.class);

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);

    }
    @PutMapping (checkOutBookUri)
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<BookResponseDto> checkOutBook(@PathVariable UUID memberId, @PathVariable UUID bookId){


        var book=bookService.checkOutBook(memberId,bookId);

        var bookResponse=mapper.map(book,BookResponseDto.class);

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);

    }
    @GetMapping (getMemberByEmailUri)
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable String email){

        var member=memberService.getMember(email);

        var memberResponse=mapper.map(member,MemberResponseDto.class);

        return new ResponseEntity<>(memberResponse, HttpStatus.OK);

    }
}
