package com.fakeBankDetails.fakeBank.filters;

import com.fakeBankDetails.fakeBank.entity.UserEntity;
import com.fakeBankDetails.fakeBank.repository.UserRepo;
import com.fakeBankDetails.fakeBank.service.interfaces.JWTServiceInterface;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver ;

    private final JWTServiceInterface jwtService ;
    private final UserRepo userRepo;

    public JWTFilter(JWTServiceInterface jwtService, UserRepo userRepo) {
        this.jwtService = jwtService;
        this.userRepo = userRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain){
    try {
        final String requestToken = request.getHeader("Authorization");

        if (requestToken == null || !requestToken.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = requestToken.split("Bearer ")[1];
        String userId = jwtService.getUserFromToken(token);

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserEntity user = userRepo.findById(userId).orElseThrow();
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }catch (Exception e){
        handlerExceptionResolver.resolveException(request , response , null , e);
    }
    }
}
