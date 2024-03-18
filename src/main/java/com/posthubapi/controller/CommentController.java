package com.posthubapi.controller;

import com.posthubapi.model.dto.Comment.EditCommentDto;
import com.posthubapi.model.dto.Comment.ReadCommentDto;
import com.posthubapi.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("api/comment")
public class CommentController {
    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<ReadCommentDto> findCommentById(@PathVariable @NotNull Long id) {
        Optional<ReadCommentDto> commentById = service.findCommentById(id);
        return commentById
                .map(readCommentDto -> ResponseEntity.ok(readCommentDto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<ReadCommentDto> editComment(@PathVariable @NotNull Long id,
                                                      @RequestBody EditCommentDto dto) {
        if (dto == null) return ResponseEntity.badRequest().build();
        Optional<ReadCommentDto> readCommentDtoOptional = service.editComment(id, dto);
        return readCommentDtoOptional
                .map(readCommentDto -> ResponseEntity.ok(readCommentDto))
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteComment(@PathVariable @NotNull Long id) {
        service.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
