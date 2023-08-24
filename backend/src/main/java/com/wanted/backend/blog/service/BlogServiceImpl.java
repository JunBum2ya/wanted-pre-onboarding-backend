package com.wanted.backend.blog.service;

import com.wanted.backend.blog.dto.BlogSaveRequest;
import com.wanted.backend.blog.entity.Blog;
import com.wanted.backend.blog.repository.BlogRepository;
import com.wanted.backend.global.util.AuthUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog createBlog(BlogSaveRequest request, Authentication authentication) {
        Blog blog = Blog.builder()
                .title(request.getTitle())
                .detail(request.getDetail())
                .share(request.getShare())
                .build();
        return blogRepository.save(blog);
    }
}
