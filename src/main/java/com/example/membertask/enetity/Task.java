package com.example.membertask.enetity;

import jakarta.persistence.*;

@Entity(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    //생
    /**
     * 아래 생성자는 JPA 에서 사용합니다.
     */
    protected Task() {
    }

    //taskCreateService 의 생성자
    public Task(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }
    //기
    public Long getMemberId() {
        return member.getId();
    }
    public Long getTaskId() {
        return id;
    }
//    public String getMemberName() {
//        return member.getMemberName();
//    }--> 서비스에서 의도가 드러나지 않아서 아래getMember 메소드로 변경

    public Member getMember() {
        return member;
    }
    public String getTaskTitle() {
        return title;
    }
    public String getTaskContent() {
        return content;
    }

    public Task update(
            String title, String content
    ) {

        this.title = title;
        this.content = content;
        return this;
    }

    public Task delete() {
        this.isDeleted = true;
        return this;
    }





}
