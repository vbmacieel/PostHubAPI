package com.posthubapi.service;

import com.posthubapi.model.dto.Comment.CreateCommentDto;
import com.posthubapi.model.dto.Comment.EditCommentDto;
import com.posthubapi.model.dto.Comment.ReadCommentDto;

import java.util.Optional;

public interface CommentService {
    Optional<ReadCommentDto> findCommentById(Long id);
    Long createCommentDto(Long postId, CreateCommentDto dto);
    Optional<ReadCommentDto> editComment(Long id, EditCommentDto dto);
    void deleteComment(Long id);
}
