package com.example.react_backend.service;

import com.example.react_backend.dao.CommentDAO;
import com.example.react_backend.model.CommentEntity;
import com.example.react_backend.model.CommentRequestDTO;
import com.example.react_backend.model.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDAO commentDAO;

    @Override
    public void write(CommentRequestDTO comment) {
        commentDAO.write(comment);
    }


    // To NOT response entity to client
    // translate entity to DTO that response to client in @Service
    @Override
    public List<CommentResponseDTO> findAll() {
        List<CommentEntity> dataList = commentDAO.findAll();
        // 자료 구조를 stream으로 변환
        List<CommentResponseDTO> commentList = dataList.stream()
                .map(
                        //(e)=>{e.target.value}
                        comment -> CommentResponseDTO.builder()
                                .commentNo(comment.getCommentNo())
                                .writer(comment.getWriter())
                                .content(comment.getContent())
                                .rank(comment.getRank())
                                .writeDate(comment.getWriteDate())
                                .build()
                )
                .collect(Collectors.toList());

        return commentList;
    }

    // 한 건을 조회하는 기능이므로 Transaction처리는 필요없지만, jpa 내부에 있는 변경 감지(엔티티가 변경여부를 jpa 내부에서 감시) 기능을사용하지 않기 위해 readOnly
    @Transactional(readOnly = true)
    @Override
    public CommentResponseDTO findByCommentNo(Long commentNo) {
        CommentEntity byCommentNo = commentDAO.findByCommentNo(commentNo);
        CommentResponseDTO commentResponseDTO = CommentResponseDTO.builder()
                .commentNo(byCommentNo.getCommentNo())
                .rank(byCommentNo.getRank())
                .writeDate(byCommentNo.getWriteDate())
                .content(byCommentNo.getContent())
                .writer(byCommentNo.getWriter())
                .build();

        return commentResponseDTO;
    }

    @Override
    public CommentResponseDTO update(Long commentNo, CommentRequestDTO comment) {
        CommentEntity resultcomment = commentDAO.update(commentNo, comment);
        return CommentResponseDTO.builder()
                .commentNo(resultcomment.getCommentNo())
                .writer(resultcomment.getWriter())
                .content(resultcomment.getContent())
                .rank(resultcomment.getRank())
                .writeDate(resultcomment.getWriteDate())
                .build();
    }

    @Override
    public String delete(Long commentNo) {
        return commentDAO.delete(commentNo);
    }
}
