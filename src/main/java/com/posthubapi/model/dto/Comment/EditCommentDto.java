package com.posthubapi.model.dto.Comment;

import javax.validation.constraints.NotBlank;

public record EditCommentDto(
        @NotBlank
        String title,
        @NotBlank
        String body
) {
}
