package com.posthubapi.service.impl;

import com.posthubapi.model.Post;
import com.posthubapi.model.dto.CreatePostDto;
import com.posthubapi.model.dto.EditPostDto;
import com.posthubapi.model.dto.ReadPostDto;
import com.posthubapi.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements com.posthubapi.service.PostService {
    private final PostRepository repository;

    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ReadPostDto> findAllPosts() {
        return repository.findAll().stream().map( post ->
                new ReadPostDto(post.getId(), post.getTitle(), post.getBody(), post.getDateOfCreation())
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<ReadPostDto> findPostById(Long id) {
        Optional<Post> postOptional = repository.findById(id);
        Optional<ReadPostDto> readPostDto = postOptional.map(post -> ReadPostDto.fromPost(post));
        return readPostDto;
    }

    @Override
    public Long createPost(CreatePostDto dto) {
        Post newPost = Post.fromCreateDto(dto);
        repository.save(newPost);
        return newPost.getId();
    }

    @Override
    public Optional<ReadPostDto> editPost(Long id, EditPostDto dto) {
        Optional<Post> postOptional = repository.findById(id);
        postOptional.ifPresent(post -> {
            post.updateFromDto(dto);
            repository.save(post);
        });

        return postOptional.map(post -> ReadPostDto.fromPost(post));
    }

    @Override
    public void deletePost(Long id) {
        Optional<Post> postOptional = repository.findById(id);
        postOptional.ifPresent(post -> repository.delete(post));
    }
}
