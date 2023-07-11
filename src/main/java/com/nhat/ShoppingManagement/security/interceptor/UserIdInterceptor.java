package com.nhat.ShoppingManagement.security.interceptor;

import com.nhat.ShoppingManagement.security.models.User;
import com.nhat.ShoppingManagement.security.repository.UserRepository;
import com.nhat.ShoppingManagement.security.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Component
public class UserIdInterceptor implements HandlerInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        // get userId from URI
        String requestURI = request.getRequestURI();
        String[] parts = requestURI.split("/");
        Long userId = Long.parseLong(parts[parts.length-1]);
        System.out.println("Pre handle userId:"+ userId+ "is call!");

        //get userId from requestToken
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
//        String token;
//        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
        String token = authorizationHeader.substring(TOKEN_PREFIX.length());
//        }

        String username = jwtUtils.getUserNameFromJwtToken(token);
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found with username: " + username));
        if (!user.getId().equals(userId))
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid User ID");
            return false;
        }
        return true;
    }
}
