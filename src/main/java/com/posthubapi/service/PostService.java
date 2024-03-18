package com.posthubapi.service;

import com.posthubapi.model.dto.CreatePostDto;
import com.posthubapi.model.dto.EditPostDto;
import com.posthubapi.model.dto.ReadPostDto;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<ReadPostDto> findAllPosts();
    Optional<ReadPostDto> findPostById(Long id);
    Long createPost(CreatePostDto dto);
    Optional<ReadPostDto> editPost(Long id, EditPostDto dto);
    void deletePost(Long id);
}
