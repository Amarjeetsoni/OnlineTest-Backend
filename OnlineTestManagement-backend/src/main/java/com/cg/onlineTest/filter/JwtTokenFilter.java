package com.cg.onlineTest.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cg.onlineTest.services.IUserService;
import com.cg.onlineTest.utils.JwtUtil;



@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	JwtUtil jwtutil;
	
	@Autowired
	IUserService userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		final String authorisationHeader=req.getHeader("Authenticate");
		String jwt=null;
		String username=null;
		
		if(authorisationHeader!=null && authorisationHeader.startsWith("Bearer ")) {
			jwt=authorisationHeader.substring(7);
			username=jwtutil.getUsernameFromToken(jwt);
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UserDetails userDetails = userDetailService.loadUserByUsername(username);
				if(jwtutil.validateToken(jwt, userDetails))
				{
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
							new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
					
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
				
			}
		}
		chain.doFilter(req, res);
		}

}
