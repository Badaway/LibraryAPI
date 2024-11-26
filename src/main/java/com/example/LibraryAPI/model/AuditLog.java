package com.example.LibraryAPI.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@ToString
@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private Date timestamp;


    @Column
    private String oldValue;

    @Column
    private String newValue;

    @Column
    private String entity;

    @Column
    private String params;

    @Column(name = "entity_id")
    private String entityId;

}
