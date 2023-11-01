package com.example.react_backend.dao;

import com.example.react_backend.model.CommentEntity;
import com.example.react_backend.model.CommentRequestDTO;
import com.example.react_backend.model.CommentResponseDTO;
import com.example.react_backend.repository.CommentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//generate constructor by using member type private final and inject
@RequiredArgsConstructor
@Repository
public class CommentDAOImpl implements CommentDAO {

    private final CommentRepo commentRepo;

    @Override
    public void write(CommentRequestDTO commentRequest) {
        //request로 넘어온 dto를 entity로 변환
        CommentEntity entity = new CommentEntity(commentRequest.getWriter(), commentRequest.getContent(), commentRequest.getRank());
        commentRepo.save(entity);
    }

    @Override
    public List<CommentEntity> findAll() {
        return commentRepo.findAll();
    }

    @Override
    public CommentEntity findByCommentNo(Long commentNo) {
        return commentRepo.findByCommentNo(commentNo);
    }

    @Transactional
    @Override
    public CommentEntity update(Long commentNo, CommentRequestDTO comment) {
        // 1. 업데이트할 레코드를 조회한다.
        // 2. 조회한 레코드의 setter메소드를 호출해서 변경할 내용을 처리
        // 데이터가 있으면 해당 데이터를 조회해서 리턴하지만 없으면 오류를 발생시키기
        // JPA가 rs를 parsing해서 entity로 만들어 return.
        // 조회한 엔티티는 영속화 -> 영속성 컨텍스트의 캐시에 이 엔티티가 등록.
        // 등록될 때 PK로 엔티티가 등록되면서 최초 객체의 상태가 snapshot으로 저장.
        CommentEntity updatecomment = commentRepo.findById(commentNo)
                .orElseThrow(()->new IllegalArgumentException("번호를 확인하세요"));
        // setter method에 의해 엔티티의 값이 변경되고, transaction이 끝나면 최초 저장된 스냅샷과 엔티티를 비교해 변화가 있는 모든 엔티티 객체를 DB에 반영한다.
        // 위와 같은 과정을 Dirty Checking이라 한다.
        // 모든 field를 update함.
        updatecomment.setContent(comment.getContent());
        updatecomment.setRank(comment.getRank());
        return updatecomment;
    }

    @Override
    public String delete(Long commentNo) {
        commentRepo.deleteById(commentNo);
        // ok가 return됐다는 의미는 정상처리 됐다는 의미
        return "ok";
    }
}
