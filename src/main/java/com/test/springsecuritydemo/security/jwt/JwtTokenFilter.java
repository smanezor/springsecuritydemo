package com.test.springsecuritydemo.security.jwt;

import com.test.springsecuritydemo.exception.JwtAuthException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider provider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        String token = provider.resolveToken((HttpServletRequest) servletRequest);

        try {
            if (StringUtils.isNotBlank(token) && provider.validateToken(token)) {
                Authentication authentication = provider.getAuthentication(token);
                if (Objects.nonNull(authentication)) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthException ex) {
            SecurityContextHolder.clearContext();
            ((HttpServletResponse) servletResponse).sendError(ex.getHttpStatus().value());
            throw new JwtAuthException("JWT token is expiration or invalid");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
