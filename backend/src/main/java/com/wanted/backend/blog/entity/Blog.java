package com.wanted.backend.blog.entity;

import com.wanted.backend.global.converter.BooleanToYNConverter;
import com.wanted.backend.global.util.DateUtil;
import com.wanted.backend.user.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "tb_blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blog_id")
    private Long id;
    @Column(length = 30,nullable = false)
    private String title;
    @Lob
    private String detail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_user_id",nullable = false)
    private final Member createMember;
    private final LocalDateTime createDate;
    private LocalDateTime updateDate;
    @Convert(converter = BooleanToYNConverter.class)
    private Boolean share;

    @Builder
    public Blog(String title,String detail,Member member,Boolean share) {
        this.title = title;
        this.detail = detail;
        this.createMember = member;
        this.createDate = DateUtil.now();
        this.updateDate = DateUtil.now();
        this.share = share != null ? share : false;
    }

    public void update(String title,String detail,Boolean share) {
        this.title = title;
        this.detail = detail;
        this.updateDate = DateUtil.now();
        this.share = share;
    }

}
