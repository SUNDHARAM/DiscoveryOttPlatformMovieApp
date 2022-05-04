package com.example.movie.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.movie.utility.JWTutility;

@Component
public class LoginJwtInterceptors extends HandlerInterceptorAdapter{
	

	@Autowired
	JWTutility jwtutil;
	
	public LoginJwtInterceptors(WebRequestInterceptor wr) {
		super();
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(!((request.getRequestURI().contains("signup")) || (request.getRequestURI().contains("login")))) {
			
			String auth = request.getHeader("validation");
			jwtutil.verifyToken(auth);
		}
		return super.preHandle(request, response, handler);
	}
}
