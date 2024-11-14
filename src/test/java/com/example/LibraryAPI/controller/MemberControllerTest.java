package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.Dto.MemberDto;
import com.example.LibraryAPI.Dto.UpdateMemberDto;
import com.example.LibraryAPI.repository.MemberRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.util.Assert;

import java.util.UUID;

import static com.example.LibraryAPI.exceptions.ExceptionMessage.bookExist;
import static com.example.LibraryAPI.exceptions.ExceptionMessage.memberExist;
import static common.Common.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void setUp() throws Exception {
        RestAssured.port = port;

    }


   

    @Test
    void testGetAllMembers_expectedMembersReturned() throws Exception {
        String token= getToken(userEmail, userPassword);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get(baseUri+MemberController.baseControllerUri+MemberController.getAllMembersUri)
                .then()
                .statusCode(200);
    }

    @Test
    void testGetMember_whenIdIsGiven_expectedMemberReturned() throws Exception {
        String token= getToken(userEmail, userPassword);

        var member=memberRepository.findAllMembersWithNoBook().get(0);
        var memberId=member.getId();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get(baseUri+MemberController.baseControllerUri+MemberController.getMemberByIdUri,memberId)
                .then()
                .statusCode(200)
                .body("name",equalTo(member.getName()));
    }

    @Test
    void TestUpdateMember_whenIdIsGiven_expectedMemberUpdated() throws Exception {
        var member=memberRepository.findAllMembersWithNoBook().get(0);
        UUID memberId=member.getId();
        var memberUpdate=new UpdateMemberDto().setName(getRandomString());
        String token=getToken(userEmail, userPassword);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body(memberUpdate)
                .when()
                .put(baseUri+MemberController.baseControllerUri+MemberController.updateMemberUri,memberId)
                .then()
                .statusCode(200)
                .body("name",equalTo(memberUpdate.getName()));
    }

    @Test
    void TestCreateMember_whenMemberIsCreated_expectedMemberCreated() throws Exception {
        String token= getToken(userEmail, userPassword);
        var memberCreate=new MemberDto().setName(getRandomString()).setEmail(getRandomString()+defaultEmail);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body(memberCreate)
                .when()
                .post(baseUri+MemberController.baseControllerUri+MemberController.createMemberUri)
                .then()
                .statusCode(201)
                .body("name",equalTo(memberCreate.getName()));
    }

    @Test
    void testDeleteMember_whenIdIsGiven_expectedMemberDeleted() throws Exception {

        String token=getToken(userEmail, userPassword);

        var members=memberRepository.findAllMembersWithNoBook();
        UUID memberId=members.get(members.size()-1).getId();

        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .delete(baseUri+MemberController.baseControllerUri+MemberController.deleteMemberUri,memberId)
                .then()
                .statusCode(204);

        boolean checkIfExist=!memberRepository.existsById(memberId);
        Assert.isTrue(checkIfExist,memberExist);
    }


}