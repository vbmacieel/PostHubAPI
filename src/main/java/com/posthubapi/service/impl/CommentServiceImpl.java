package com.posthubapi.service.impl;

import com.posthubapi.model.Comment;
import com.posthubapi.model.Post;
import com.posthubapi.model.dto.Comment.CreateCommentDto;
import com.posthubapi.model.dto.Comment.EditCommentDto;
import com.posthubapi.model.dto.Comment.ReadCommentDto;
import com.posthubapi.repository.CommentRepository;
import com.posthubapi.repository.PostRepository;
import com.posthubapi.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Optional<ReadCommentDto> findCommentById(Long id) {
        return commentRepository.findById(id).map(comment -> ReadCommentDto.fromComment(comment));
    }

    @Override
    public Long createCommentDto(Long postId, CreateCommentDto dto) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            Comment newComment = Comment.fromCreateDto(dto);
            post.addComment(newComment);
            newComment.setPost(post);
            commentRepository.save(newComment);
            return newComment.getId();
        }

        return null;
    }

    @Override
    public Optional<ReadCommentDto> editComment(Long id, EditCommentDto dto) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        commentOptional.ifPresent(comment -> {
          comment.updateFromDto(dto);
          commentRepository.save(comment);
        });

        return commentOptional.map(comment -> ReadCommentDto.fromComment(comment));
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.findById(id).ifPresent(commentRepository::delete);
    }
}
