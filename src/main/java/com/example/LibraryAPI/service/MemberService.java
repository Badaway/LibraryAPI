package com.example.LibraryAPI.service;

import com.example.LibraryAPI.model.Member;
import com.example.LibraryAPI.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository)
    {
        this.memberRepository=memberRepository;
    }

    public Member getMember(int id){
        Optional<Member> memberOptional =memberRepository.findById(id);


        return memberOptional.orElse(null);
    }

    public List<Member> getAllMembers(){

        List<Member> members = new ArrayList<>();

        memberRepository.findAll().forEach(members::add);
        return members;

        /*
        if (!members.isEmpty())
            return new ResponseEntity<>( members, HttpStatus.OK);
        return new ResponseEntity<>( "NA ", HttpStatus.BAD_REQUEST);*/
    }

    public Member newMember(Member member){
        return memberRepository.save(member);
    }

    public ResponseEntity<Object> deleteMember(int id){

        memberRepository.deleteById(id);

        return  new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
    public Member updateMember(Member member){
            return memberRepository.save(member);

    }
}
