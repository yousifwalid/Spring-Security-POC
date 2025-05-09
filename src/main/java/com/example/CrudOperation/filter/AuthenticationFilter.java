
package com.example.CrudOperation.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {  //Check on each request which gets in.(&Check on jwtToken).Each request should be authenticated.

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");  //"Authorization"is the header name that contains jwt Token(or Bearer token)
        final String jwt;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {            //Bearer is a token
            filterChain.doFilter(request, response);
            return;
        }
        //Extract JwtToken from the authHeader(Authorization)
        jwt = authHeader.substring(7);         //count Bearer with Space = 7 String.

        //Extract Username from the jwtToken.
        username = jwtService.extractUsername(jwt);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {  //if we have user &user is not authenticated
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);  //then get userDetails from DB.
            if (jwtService.isTokenValid(jwt, userDetails)) {    //then check if user & token is valid
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                //then extend the authToken with details of our request in it.
                authToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(request)
                );
                //Final Step: Update the SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
//UsernamePasswordAuthenticationToken

//Page 260 in Spring Security in action Book
//Create an instance of SecureRandom that generate a strong random number (for OTP)
//SecureRandom random = SecureRandom.getInstanceStrong();