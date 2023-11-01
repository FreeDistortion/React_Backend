package com.example.react_backend.service;

import com.example.react_backend.model.CommentEntity;
import com.example.react_backend.model.CommentRequestDTO;
import com.example.react_backend.model.CommentResponseDTO;

import java.util.List;

public interface CommentService {
    void write(CommentRequestDTO comment);
    List<CommentResponseDTO> findAll();
    CommentResponseDTO findByCommentNo(Long commentNo);

    CommentResponseDTO update(Long commentNo, CommentRequestDTO comment);
    String delete(Long commentNo);
}

