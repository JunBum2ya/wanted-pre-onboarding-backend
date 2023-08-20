package com.wanted.backend.user.repository;

import com.wanted.backend.user.entity.Authority;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;

import java.beans.Transient;

@SpringBootTest
public class AuthorityRepositoryTest {

    @Autowired
    AuthorityRepository authorityRepository;

    @BeforeEach
    public void initAuthority() {
        Authority authority = Authority.builder()
                .name("MEMBER")
                .comment("일반인")
                .build();
        authorityRepository.save(authority);
    }

    @Test
    public void save() {
        Authority authority = Authority.builder()
                .name("ADMIN")
                .comment("관리자")
                .build();
        Authority savedAuthority = authorityRepository.save(authority);
        Assertions.assertEquals(savedAuthority.getName(),authority.getName());
    }

    @Test
    @Transactional
    public void findOne() {
        authorityRepository.findById("MEMBER").ifPresentOrElse(o -> {
            Assertions.assertEquals(o.getName(),"MEMBER");
        },() -> Assertions.assertEquals(1,0));
    }

    @Test
    @Transactional
    public void update() {
        String text = "12345";
        Authority memberAuthority = authorityRepository.findById("MEMBER").get();
        memberAuthority.update(text);
        System.out.println(memberAuthority.getComment());
        authorityRepository.findById("MEMBER").ifPresentOrElse(o -> {
            Assertions.assertEquals(o.getComment(),text);
        },() -> Assertions.assertEquals(1,0));
    }

    @Test
    @Transactional
    public void delete() {
        Authority memberAuthority = authorityRepository.findById("MEMBER").get();
        authorityRepository.delete(memberAuthority);
        Assertions.assertTrue(authorityRepository.findById("MEMBER").isEmpty());
    }

}
