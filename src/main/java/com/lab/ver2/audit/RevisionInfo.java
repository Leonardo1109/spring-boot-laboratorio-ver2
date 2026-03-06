package com.lab.ver2.audit;

import java.time.Instant;

import org.hibernate.envers.*;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@RevisionEntity(CustomRevisionListener.class)
@Table(name = "revinfo")
@Data
public class RevisionInfo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private Integer id;

    @RevisionTimestamp
    @Column(name = "fecha", nullable = false)
    private Instant fecha;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username")
    private String username;
}
