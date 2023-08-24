package com.wanted.backend.blog.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogSaveRequest {
    private Long id;
    @Max(value = 30)
    private String title;
    private String detail;
    private Boolean share;
}
