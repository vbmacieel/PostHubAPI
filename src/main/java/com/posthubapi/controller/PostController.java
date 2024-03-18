package com.posthubapi.controller;

import com.posthubapi.model.dto.Comment.CreateCommentDto;
import com.posthubapi.model.dto.Comment.ReadCommentDto;
import com.posthubapi.model.dto.CreatePostDto;
import com.posthubapi.model.dto.EditPostDto;
import com.posthubapi.model.dto.ReadPostDto;
import com.posthubapi.service.CommentService;
import com.posthubapi.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<ReadPostDto>> findAllPosts() {
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @GetMapping("{id}")
    public ResponseEntity<ReadPostDto> findPostById(@PathVariable Long id) {
        Optional<ReadPostDto> postDto = postService.findPostById(id);
        return postDto
                .map(post -> ResponseEntity.ok(post))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CreatePostDto> createPost(@RequestBody CreatePostDto dto,
                                                    UriComponentsBuilder uriBuilder) {
        if (dto == null) return ResponseEntity.badRequest().build();
        Long postId = postService.createPost(dto);
        UriComponents uriComponents = uriBuilder.path("/api/post/{id}").buildAndExpand(postId);
        URI location = uriComponents.toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("{id}/comment")
    public ResponseEntity<ReadCommentDto> createCommentOnPost(@PathVariable @NotNull Long id, @RequestBody CreateCommentDto dto,
                                                              UriComponentsBuilder uriBuilder) {
        if (dto == null) return ResponseEntity.badRequest().build();
        Long commentId = commentService.createCommentDto(id, dto);
        UriComponents uriComponents = uriBuilder.path("/api/comment/{id}").buildAndExpand(commentId);
        URI location = uriComponents.toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ReadPostDto> editPost(@PathVariable @NotNull Long id,
                                                @RequestBody EditPostDto dto) {
        if (dto == null) return ResponseEntity.badRequest().build();
        Optional<ReadPostDto> readPostDto = postService.editPost(id, dto);

        return readPostDto
                .map(postEdited -> ResponseEntity.ok(postEdited))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePost(@PathVariable @NotNull Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
