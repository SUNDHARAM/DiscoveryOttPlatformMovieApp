//package com.example.movie.common;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//
//@ControllerAdvice
//public class ExceptionHandlers{
//	
//	@ExceptionHandler
//	public ResponseEntity<APIResponse> handleAccessDeniedExcepion(AccessDeniedException e) {
//		
//		APIResponse apiresponse = new APIResponse();
//		apiresponse.setStatus(HttpStatus.UNAUTHORIZED.value());
//		apiresponse.setData(e);
//		return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
//	}
//
//}
