package com.example.react_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Table(name="mycomment")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
// 최초 snapshot 대비 변경 감지한 부분만 바꿀 수 있도록 하는 annotation
@DynamicUpdate
public class CommentEntity {
    @Id
    @GeneratedValue
    private Long commentNo;

    @Column(nullable = false)
    private String writer;
    private String content;
    private int rank;

    @CreationTimestamp
    private Date writeDate;

    public CommentEntity(String writer, String content, int rank) {
        this.writer = writer;
        this.content = content;
        this.rank = rank;
    }
}
