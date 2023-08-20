package com.wanted.backend.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_authority")
public class Authority {
    @Id
    @Column(name = "authority_name",length = 30)
    private String name;
    @Column(length = 50)
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
