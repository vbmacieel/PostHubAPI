package com.posthubapi.model.dto;

import com.posthubapi.model.Post;

import java.util.Date;

public record ReadPostDto (
   Long id,
   String title,
   String body,
   Date dateOfCreation
) {
    public ReadPostDto(Long id, String title, String body, Date dateOfCreation) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.dateOfCreation = dateOfCreation;
    }

    public static ReadPostDto fromPost(Post post) {
        return new ReadPostDto(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getDateOfCreation()
        );
    }
}
