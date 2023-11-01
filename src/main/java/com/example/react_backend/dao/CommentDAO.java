package com.example.react_backend.dao;

import com.example.react_backend.model.CommentEntity;
import com.example.react_backend.model.CommentRequestDTO;
import com.example.react_backend.model.CommentResponseDTO;

import java.util.List;

public interface CommentDAO {
    void write(CommentRequestDTO commentRequest);
    List<CommentEntity> findAll();
    CommentEntity findByCommentNo(Long commentNo);
    CommentEntity update(Long commentNo, CommentRequestDTO comment);
    String delete(Long commentNo);
}
