package com.posthubapi.model.dto;

import javax.validation.constraints.NotBlank;

public record EditPostDto (
        @NotBlank
        String title,
        @NotBlank
        String body
) {
}
