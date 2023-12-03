package com.labreport.backendlabrep.security;

import java.io.IOException;
// import io.jsonwebtoken.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.labreport.backendlabrep.service.JwtService;
import com.labreport.backendlabrep.service.SecurityService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthFiltre extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final SecurityService securityService;

    public JwtAuthFiltre(JwtService jwtService, SecurityService securityService) {
        this.jwtService = jwtService;
        this.securityService = securityService;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException,IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if(authHeader != null && authHeader.startsWith("Bearer")){
            token = authHeader.substring(7);
            username = jwtService.extractUser(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){//SecurityiContextHolder tam olarak ne işe yarıyor?
            UserDetails user = securityService.loadUserByUsername(username);//Bunu anlamadım.
            log.info("user yuklendi: "+user);
            if(jwtService.validateToken(token, user)){//WARN=> Bunu daha fazla incele.
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//Burda naptık lan??
                SecurityContextHolder.getContext().setAuthentication(authToken);//ne holdırmışsın be
            }
        }
        
        filterChain.doFilter(request, response);//Filter işlemine devam et.
    }
}
