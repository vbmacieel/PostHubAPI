package com.posthubapi.model;

import com.posthubapi.model.dto.CreatePostDto;
import com.posthubapi.model.dto.EditPostDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String title;
        private String body;
        private Date dateOfCreation = new Date();
        @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
        private List<Comment> comments = new ArrayList<>();

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
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

        public List<Comment> getComments() {
                return comments;
        }

        public void addComment(Comment comment) {
                this.comments.add(comment);
        }

        public static Post fromCreateDto(CreatePostDto dto) {
                Post post = new Post();
                post.title = dto.title();
                post.body = dto.body();
                return post;
        }

        public void updateFromDto(EditPostDto dto) {
                this.title = dto.title();
                this.body = dto.body();
        }
}