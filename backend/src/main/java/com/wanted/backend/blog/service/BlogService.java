package com.wanted.backend.blog.service;

import com.wanted.backend.blog.dto.BlogSaveRequest;
import com.wanted.backend.blog.entity.Blog;
import org.springframework.security.core.Authentication;

public interface BlogService {
    public Blog createBlog(BlogSaveRequest request, Authentication authentication);
}
