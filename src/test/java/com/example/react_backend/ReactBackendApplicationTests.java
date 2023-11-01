package com.example.react_backend;

import com.example.react_backend.model.CommentEntity;
import com.example.react_backend.repository.CommentRepo;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ReactBackendApplicationTests {

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    EntityManager entityManager;

    @Test
    @Disabled
    void test1(){
        System.out.println(commentRepo.getClass()+"-----------------------");
    }

    @Test
    void test2(){
        //영속성 컨텍스트(persistence context)
        System.out.println("B4 call method");
        CommentEntity entity1=new CommentEntity("writer","content",5);
        entityManager.persist(entity1);
        entityManager.persist(entity1);
        entityManager.persist(entity1);
        entityManager.persist(new CommentEntity("writer2","content2",4));
        System.out.println("After call method");

        //SQL command를 모두 실행
        entityManager.flush();

        //cache를 비움
        entityManager.clear();
        System.out.println("==================================");

        CommentEntity findEntity = entityManager.find(CommentEntity.class, entity1.getCommentNo());

        System.out.println(findEntity);

    }

}
