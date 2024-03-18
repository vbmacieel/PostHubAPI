package com.posthubapi.model.dto;

import javax.validation.constraints.NotBlank;

public record CreatePostDto(
        @NotBlank
        String title,
        @NotBlank
        String body) {
}
