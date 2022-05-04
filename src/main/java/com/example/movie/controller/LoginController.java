package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.common.APIResponse;
import com.example.movie.dto.LoginRequestDTO;
import com.example.movie.dto.SignUpRequestDTO;
import com.example.movie.service.LoginService;
import com.example.movie.utility.JWTutility;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private JWTutility jwtutility;
	
	
	@PostMapping("/signup")
	public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signuprequestdto) {
		
		APIResponse apires = loginService.signUp(signuprequestdto);
		
		return ResponseEntity.status(apires.getStatus()).body(apires);
	}

	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginrequestdto) {
		
		APIResponse apires = loginService.login(loginrequestdto);
		
		return ResponseEntity.status(apires.getStatus()).body(apires);
	}
	
	@GetMapping("/validation")
	public ResponseEntity<APIResponse> privateApi(@RequestHeader(value = "validation", defaultValue = "") String auth) throws Exception {
		
		APIResponse apires = new APIResponse();
		try {
			jwtutility.verifyToken(auth);
			apires.setData("Validation Successful....");
			return ResponseEntity.status(apires.getStatus()).body(apires);
		}
		catch(Exception e) {
			apires.setData("Validation Failed....");
			return ResponseEntity.status(apires.getStatus()).body(apires);
		}
	}
}
