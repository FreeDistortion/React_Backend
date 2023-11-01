package com.example.react_backend.controller;

import com.example.react_backend.model.CommentRequestDTO;
import com.example.react_backend.model.CommentResponseDTO;
import com.example.react_backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {
    private final CommentService commentService;

    //그 때마다 다르게 response할 때 ResponseEntity<?>
    //@RequestBody: json 또는 dto로 통신
    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestBody CommentRequestDTO request){
        // call write method of service 이후 parameter로 전달되는 request object를 넘겨줌.
        //요청이 성공하는 경우 request no.200
//        System.out.println(request);
        commentService.write(request);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        List<CommentResponseDTO> allDatas = commentService.findAll();
        return ResponseEntity.ok(allDatas);
    }

    // @PathVariable로 조회
    @GetMapping("/read/{commentNo}")
    public ResponseEntity<?> read(@PathVariable Long commentNo){
        CommentResponseDTO byCommentNo = commentService.findByCommentNo(commentNo);
        return ResponseEntity.ok(byCommentNo);
    }

    @PutMapping("/update/{commentNo}")
    public ResponseEntity<?> update(@PathVariable Long commentNo, @RequestBody CommentRequestDTO commentRequestDTO){
        return ResponseEntity.ok(commentService.update(commentNo,commentRequestDTO));
    }

    @DeleteMapping("/delete/{commentNo}")
    public ResponseEntity<?> delete(@PathVariable Long commentNo){
        return ResponseEntity.ok(commentService.delete(commentNo));
    }
}
