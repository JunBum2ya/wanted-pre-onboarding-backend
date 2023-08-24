package com.wanted.backend.user.entity;

import com.wanted.backend.global.util.DateUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "tb_authority")
public class Authority {
    @Id
    @Column(name = "authority_name",length = 30)
    private String name;
    @Column(length = 50)
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Authority(String name) {
        this(name,null);
    }

    @Builder
    private Authority(String name,String comment) {
        this.name = name;
        this.comment = comment;
    }

    public void update(String name,String comment) {
        this.update(comment);
        this.name = name;
    }

    public void update(String comment) {
        this.comment = comment;
        this.updateDate = DateUtil.now();
    }

}
