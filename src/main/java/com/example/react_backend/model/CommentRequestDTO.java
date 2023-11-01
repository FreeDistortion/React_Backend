package com.example.react_backend.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentRequestDTO {

    private String writer;
    private String content;
    private int rank;

}
