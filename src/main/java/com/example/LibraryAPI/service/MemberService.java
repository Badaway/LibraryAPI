package com.example.LibraryAPI.service;

import com.example.LibraryAPI.Dto.MemberDto;
import com.example.LibraryAPI.Dto.MemberResponseDto;
import com.example.LibraryAPI.Dto.UpdateMemberDto;
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

@Service
public class MemberService {

    @Autowired
    private  MemberRepository memberRepository;

    @Autowired
    private Mapper mapper;


    public MemberResponseDto getMember(UUID id){
        var member =memberRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Member not found with id " + id));


        return mapper.map(member,MemberResponseDto.class);
    }

    public List<MemberResponseDto> getAllMembers(){

        var members=memberRepository.findAll();

        var membersResponse =new ArrayList<MemberResponseDto>();

        for (Member member:members)
        {
            var memberResponseDto =mapper.map(member, MemberResponseDto.class);
            membersResponse.add(memberResponseDto);
        }

        return  membersResponse;

    }

    public MemberDto createMember(MemberDto member){
        
        var memberToSave=mapper.map(member,Member.class);
        
        memberRepository.save(memberToSave);
        
        return member;
    }

    public void deleteMember(UUID id){

        if(memberRepository.existsById(id)) {
            memberRepository.deleteById(id);

        }

        throw new NoSuchElementException("There is no member with id : "+id);
    }
    public MemberDto updateMember(UpdateMemberDto member, UUID id){


        var returnedMember= memberRepository.findById(id)
                                .orElseThrow(() -> new NoSuchElementException("Member not found with id " + id));

        if(member.getName()!=null && !member.getName().isEmpty()){
            returnedMember.setName(member.getName());
        }


        var savedMember =memberRepository.save(returnedMember);

        return mapper.map(savedMember, MemberDto.class);

    }
    public MemberResponseDto getMember(String email){
        var member =memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Member not found with email " + email));


        return mapper.map(member,MemberResponseDto.class);
    }
}
