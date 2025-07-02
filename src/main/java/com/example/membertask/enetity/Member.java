package com.example.membertask.enetity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberName;

    private String email;

    private String password;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private Boolean isDeleted = false;


    //생

    /**
     * 아래 생성자는 JPA 에서 사용합니다.
     */
    protected Member() {
    }

    /**
     *아래의 생성자를 private 으로 캡슐화를 해
     * createFromMemberCreateRequestDto 메소드를 이용하도록 하여
     * 의도를 드러냅니다.
     *  --> MemberCreateRequestDto 정보를 꺼내와
     *      * MemberService 의 memberCreateService 메소드에서
     *      * entity 만들때 씁니다.
     */
    public Member(
            String memberName, String email, String password
    ) {
        this.memberName = memberName;
        this.email = email;
        this.password = password;
    }

    public Member(Long memberId) {


    }
//    public static Member createFromMemberCreateRequestDto(
//            String memberName, String email, String password) {
//        return new Member(memberName, email, password);
//    }

    public Long getId() {
        return id;
    }
    public String getMemberName() {
        return memberName;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public Member update(
            String memberName, String email, String password
    ) {
        this.memberName = memberName;
        this.email = email;
        this.password = password;

        return this;
    }
    public Member delete() {
        this.isDeleted = true;
        return this;
    }

    //기
    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        this.createdAt = now;
        this.updatedAt = now;
    }
    @PreUpdate
    public void onUpdate() {

        this.updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    }
}
