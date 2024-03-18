package com.posthubapi.model;

import com.posthubapi.model.dto.Comment.CreateCommentDto;
import com.posthubapi.model.dto.Comment.EditCommentDto;
import com.posthubapi.model.dto.CreatePostDto;
import com.posthubapi.model.dto.EditPostDto;
import com.posthubapi.repository.CommentRepository;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    private Date dateOfCreation = new Date();
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public static Comment fromCreateDto(CreateCommentDto dto) {
        Comment comment = new Comment();
        comment.body = dto.body();
        return comment;
    }

    public void updateFromDto(EditCommentDto dto) {
        this.body = dto.body();
    }
}
