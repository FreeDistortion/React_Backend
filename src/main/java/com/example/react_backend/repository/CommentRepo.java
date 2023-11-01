package com.example.react_backend.repository;

import com.example.react_backend.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<CommentEntity,Long> {
    CommentEntity findByCommentNo(Long commentNo);
}
