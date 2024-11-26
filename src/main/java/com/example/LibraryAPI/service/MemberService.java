package com.example.LibraryAPI.service;

import com.example.LibraryAPI.Dto.*;
import com.example.LibraryAPI.exceptions.ExceptionMessage;
import com.example.LibraryAPI.mapping.Mapper;
import com.example.LibraryAPI.model.AuditLog;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.model.Member;
import com.example.LibraryAPI.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

import static com.example.LibraryAPI.exceptions.ExceptionMessage.memberNotFoundByEmail;
import static com.example.LibraryAPI.exceptions.ExceptionMessage.memberNotFoundById;

@Service
public class MemberService {

    @Autowired
    private  MemberRepository memberRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private AuditService auditService;

    @Autowired
    private BookService bookService;


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

        var memberToCreate =memberRepository.save(memberToSave);


        var log= new AuditLog()
                .setAction("Create")
                .setEntityId(memberToCreate.getId().toString())
                .setEntity(memberToCreate.getClass().getSimpleName())
                .setNewValue(memberToCreate.toString());
        auditService.createLog(log);

        return memberToCreate;
        
        //return member;
    }

    public void deleteMember(UUID id){

        var memberToDelete = memberRepository.findById(id).orElseThrow();

        var log =new AuditLog()
                .setAction("Delete")
                .setEntityId(memberToDelete.getId().toString())
                .setEntity(memberToDelete.getClass().getSimpleName())
                .setOldValue(memberToDelete.toString());
        auditService.createLog(log);

                memberRepository.deleteById(id);
    }
    public Member updateMember(UpdateMemberDto member, UUID id){


        var returnedMember= memberRepository.findById(id)
                                .orElseThrow(() -> new NoSuchElementException(memberNotFoundById+ id));

        var log = new AuditLog()
                .setEntityId(returnedMember.getId().toString())
                .setOldValue(returnedMember.toString())
                .setAction("Update")
                .setEntity(returnedMember.getClass().getSimpleName())
                .setNewValue(member.toString());

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

    public List<MemberBorrowedBook> getAllMemberBorrowedBooks(String fromDate,String toDate) throws ParseException {

        var books=bookService.getBooksBorrowedFromTo(fromDate,toDate);
        Map<Member, List<BookResponseDto>> borrowedBooks= new Hashtable<>();

        for (Book book:books){
            var member=book.getMember();
            var bookResponseDto=mapper.map(book,BookResponseDto.class);
            if(borrowedBooks.containsKey(member)){

             borrowedBooks.get(member).add(bookResponseDto);
            }
            else {
                borrowedBooks.put(member, new ArrayList<>());


                borrowedBooks.get(member).add(bookResponseDto);
            }
        }
        List<MemberBorrowedBook> membersBorrowedBooks= new ArrayList<>();

        for (Map.Entry<Member, List<BookResponseDto>> memberBorrowedBooks : borrowedBooks.entrySet()){
            var borrowedBooksValue=memberBorrowedBooks.getValue();
            var memberKey=memberBorrowedBooks.getKey();
            MemberBorrowedBook member=new MemberBorrowedBook()
                    .setBorrowedBooks(borrowedBooksValue)
                    .setTotalBooks(borrowedBooksValue.size())
                    .setId(String.valueOf(memberKey.getId()))
                    .setName(memberKey.getName());

            membersBorrowedBooks.add(member);
        }

            return  membersBorrowedBooks;
    }
}
