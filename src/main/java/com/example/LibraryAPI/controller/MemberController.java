package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.Dto.BookResponseDto;
import com.example.LibraryAPI.Dto.MemberDto;
import com.example.LibraryAPI.Dto.MemberResponseDto;
import com.example.LibraryAPI.Dto.UpdateMemberDto;
import com.example.LibraryAPI.service.BookService;
import com.example.LibraryAPI.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;



@RequestMapping(MemberController.baseControllerUri)
@RestController
public class MemberController {

    public static final String baseControllerUri = "/member";

    @Autowired
    private  MemberService memberService;

    @Autowired
    private  BookService bookService;




    @GetMapping("/members")
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<Object> getAllMembers() {

        var members= memberService.getAllMembers();

        return new ResponseEntity<>(members,HttpStatus.OK);
    }
    @GetMapping ("id/{memberId}")
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable UUID memberId){

        var member=memberService.getMember(memberId);

        return new ResponseEntity<>(member, HttpStatus.OK);

    }

    @PutMapping("/{memberId}")
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<Object> updateMember(@PathVariable UUID memberId,@Valid @RequestBody UpdateMemberDto member){

        var memberResponse=memberService.updateMember(member,memberId);

        return new ResponseEntity<>(memberResponse,HttpStatus.OK);

    }

    @PostMapping ("/")
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<MemberDto> createMember(@Valid @RequestBody MemberDto member){

        var memberCreate=memberService.createMember(member);

        return  new ResponseEntity<>(memberCreate, HttpStatus.CREATED);

    }

    @DeleteMapping ("/{memberId}")
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<String> deleteMember( @PathVariable UUID memberId){

        memberService.deleteMember(memberId);

        return ResponseEntity.noContent().build();

    }

    @PutMapping ("/{memberId}/borrow/{bookId}")
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<Object> borrowBook( @PathVariable UUID memberId,@PathVariable UUID bookId){

        var book=bookService.borrowBook(memberId,bookId);

        return new ResponseEntity<>(book, HttpStatus.OK);

    }
    @PutMapping ("/{memberId}/checkOut/{bookId}")
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<BookResponseDto> checkOutBook(@PathVariable UUID memberId, @PathVariable UUID bookId){


        var book=bookService.checkOutBook(memberId,bookId);


        return new ResponseEntity<>(book, HttpStatus.OK);

    }
    @GetMapping ("/{email}")
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable String email){

        var member=memberService.getMember(email);

        return new ResponseEntity<>(member, HttpStatus.OK);

    }
}
