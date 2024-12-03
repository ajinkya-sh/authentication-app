package com.ajinkya.authenticationApp.interceptor;

import com.ajinkya.authenticationApp.annotation.JwtSecured;
import com.ajinkya.authenticationApp.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod handlerMethod) {
            if (handlerMethod.getMethod().isAnnotationPresent(JwtSecured.class)) {
                String authHeader = request.getHeader("Authorization");
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization token is missing");
                    return false;
                }
                String token = authHeader.substring(7);

                try {
                    String username = JwtUtil.extractClaims(token).getSubject();
                    JwtUtil.validateToken(token, username);
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is invalid or expired");
                    return false;
                }
            }
        }
        return true;
    }
}
