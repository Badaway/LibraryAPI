package com.example.LibraryAPI.service;

import com.example.LibraryAPI.Dto.MemberDto;
import com.example.LibraryAPI.Dto.MemberResponseDto;
import com.example.LibraryAPI.Dto.UpdateMemberDto;
import com.example.LibraryAPI.exceptions.ExceptionMessage;
import com.example.LibraryAPI.mapping.Mapper;
import com.example.LibraryAPI.model.Member;
import com.example.LibraryAPI.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.example.LibraryAPI.exceptions.ExceptionMessage.memberNotFoundByEmail;
import static com.example.LibraryAPI.exceptions.ExceptionMessage.memberNotFoundById;

@Service
public class MemberService {

    @Autowired
    private  MemberRepository memberRepository;

    @Autowired
    private Mapper mapper;


    public Member getMember(UUID id){
        return  memberRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException(memberNotFoundById + id));


      //  return mapper.map(member,MemberResponseDto.class);
    }

    public List<Member> getAllMembers(){

        return memberRepository.findAll();



    }

    public Member createMember(MemberDto member){
        
        var memberToSave=mapper.map(member,Member.class);
        
        return memberRepository.save(memberToSave);
        
        //return member;
    }

    public void deleteMember(UUID id){

        if(memberRepository.existsById(id)) {
            memberRepository.deleteById(id);

        }

        else {
            throw new NoSuchElementException(memberNotFoundById + id);
        }
    }
    public Member updateMember(UpdateMemberDto member, UUID id){


        var returnedMember= memberRepository.findById(id)
                                .orElseThrow(() -> new NoSuchElementException(memberNotFoundById+ id));

        if(member.getName()!=null && !member.getName().isEmpty()){
            returnedMember.setName(member.getName());
        }


        return memberRepository.save(returnedMember);

       // return mapper.map(savedMember, MemberDto.class);

    }
    public Member getMember(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException(memberNotFoundByEmail+ email));


      //  return mapper.map(member,MemberResponseDto.class);
    }
}
