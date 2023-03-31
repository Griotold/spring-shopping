package com.example.service;

import com.example.dto.MemberFormDto;
import com.example.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    // 테스트를 위한 dto + Member의 createMember 호출
    public Member createMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@gmail.com");
        memberFormDto.setName("홍길동");
        memberFormDto.setAddress("서울시 마포구 합정동");
        memberFormDto.setPassword("1234");
        return Member.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() throws Exception {
        // given
        Member member = createMember();

        // when
        Member savedMember = memberService.saveMember(member);

        // then
        assertThat(member.getEmail()).isEqualTo(savedMember.getEmail());
        assertThat(member.getName()).isEqualTo(savedMember.getName());
        assertThat(member.getAddress()).isEqualTo(savedMember.getAddress());
        assertThat(member.getPassword()).isEqualTo(savedMember.getPassword());
        assertThat(member.getRole()).isEqualTo(savedMember.getRole());
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateMemberTest()throws Exception {
        // given
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);

        // when
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            memberService.saveMember(member2);
        });

        // then
        assertThat(exception.getMessage()).isEqualTo("이미 가입된 회원입니다.");
    }

}