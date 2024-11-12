package com.example.LibraryAPI.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Data
@Accessors(chain = true)
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
