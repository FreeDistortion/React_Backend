package com.example.react_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentResponseDTO {

    private Long commentNo;
    private String writer;
    private String content;
    private int rank;
    private Date writeDate;

    public CommentResponseDTO(CommentEntity entity){
        this.commentNo = entity.getCommentNo();
        this.writer = entity.getWriter();
        this.content = entity.getContent();
        this.rank = entity.getRank();
        this.writeDate = entity.getWriteDate();
    }

}
