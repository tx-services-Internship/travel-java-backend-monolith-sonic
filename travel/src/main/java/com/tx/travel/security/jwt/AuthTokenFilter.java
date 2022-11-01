package com.tx.travel.security.jwt;

import com.tx.travel.security.services.UserDetailsServiceImpl;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthTokenFilter extends OncePerRequestFilter {
  @Autowired private JwtUtils jwtUtils;

  @Autowired private UserDetailsServiceImpl userDetailsService;

  private static final Logger log = LoggerFactory.getLogger(AuthTokenFilter.class);

  @Override
  protected void doFilterInternal(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final FilterChain filterChain)
      throws ServletException, IOException {
    try {
      final String jwt = parseJwt(request);
      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
        final String username = jwtUtils.getUserNameFromJwtToken(jwt);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        final UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (final Exception e) {
      log.error("Cannot set user authentication", e);
    }

    filterChain.doFilter(request, response);
  }

  private String parseJwt(final HttpServletRequest request) {
    return jwtUtils.getJwtFromCookies(request);
  }
}
