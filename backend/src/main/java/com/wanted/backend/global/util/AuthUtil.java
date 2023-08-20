package com.wanted.backend.global.util;

import com.wanted.backend.user.dto.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Slf4j
public class AuthUtil {

    public static Optional<CustomUserDetails> getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            log.debug("Security Context에 인증정보가 없습니다.");
            return Optional.empty();
        }
        CustomUserDetails customUserDetails = null;
        if(authentication.getPrincipal() instanceof CustomUserDetails) {
            customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        }
        return Optional.ofNullable(customUserDetails);
    }

}
