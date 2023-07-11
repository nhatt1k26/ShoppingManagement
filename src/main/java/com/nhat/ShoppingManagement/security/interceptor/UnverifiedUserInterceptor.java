package com.nhat.ShoppingManagement.security.interceptor;

import com.nhat.ShoppingManagement.security.exception.UnverifiedAccountException;
import com.nhat.ShoppingManagement.security.models.User;
import com.nhat.ShoppingManagement.security.repository.UserRepository;
import com.nhat.ShoppingManagement.security.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class UnverifiedUserInterceptor implements HandlerInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //get userId from requestToken
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        String token;
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            token = authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        else throw new Exception("AuthorizationHeader not found!");

        String username = jwtUtils.getUserNameFromJwtToken(token);
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found with username: " + username));
        if (user.getVerifiedEmail().equals(Boolean.FALSE))
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid User ID");
            return false;
        }
        return true;
    }
}
