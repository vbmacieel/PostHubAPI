package com.posthubapi.model.dto.Comment;

import com.posthubapi.model.Comment;
import com.posthubapi.model.Post;

import java.util.Date;

public record ReadCommentDto(
   Long id,
   String body,
   Date dateOfCreation
) {
    public ReadCommentDto(Long id, String body, Date dateOfCreation) {
        this.id = id;
        this.body = body;
        this.dateOfCreation = dateOfCreation;
    }

    public static ReadCommentDto fromComment(Comment comment) {
        return new ReadCommentDto(
                comment.getId(),
                comment.getBody(),
                comment.getDateOfCreation()
        );
    }
}
