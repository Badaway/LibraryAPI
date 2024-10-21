package com.example.LibraryAPI.Dto;

import jakarta.persistence.Column;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

public class MemberResponseDto {

    private String name;

    private String email;

    private Date membershipDate;

    public String getName() {
        return name;
    }

    public MemberResponseDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public MemberResponseDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getMembershipDate() {
        return membershipDate;
    }

    public MemberResponseDto setMembershipDate(Date membershipDate) {
        this.membershipDate = membershipDate;
        return this;
    }
}
