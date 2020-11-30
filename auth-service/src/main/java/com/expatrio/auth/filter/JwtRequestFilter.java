package com.expatrio.auth.filter;

import com.expatrio.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class JwtRequestFilter// extends OncePerRequestFilter
 {

//	@Qualifier("customUserDetailsService")
//	@Autowired
//	private CustomUserDetailsService userDetailsService;

//	@Autowired
//	private JwtUtil jwtUtil;

//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//			throws ServletException, IOException {

//		final String authorizationHeader = request.getHeader("Authorization");
//
//		String email = null;
//		String jwt = null;
//
//		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//			jwt = authorizationHeader.substring(7);
//			email = jwtUtil.extractUsername(jwt);
//		}
//
//		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//			UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
//
//			if (jwtUtil.validateToken(jwt, email)) {
//
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//						userDetails, null, userDetails.getAuthorities());
//				usernamePasswordAuthenticationToken
//						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//			}
//		}
//		chain.doFilter(request, response);
//	}

}
