package com.example.LibraryAPI.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @UpdateTimestamp
    @Column(name = "membership_date")
    private Date membershipDate;

    public Integer getId() {
        return id;
    }

    public Member setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Member setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Member setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getMembershipDate() {
        return membershipDate;
    }

    public Member setMembershipDate(Date membershipDate) {
        this.membershipDate = membershipDate;
        return this;
    }
}
