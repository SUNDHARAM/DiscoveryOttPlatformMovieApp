package com.example.movie.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movie.bean.User;
import com.example.movie.common.APIResponse;
import com.example.movie.dto.LoginRequestDTO;
import com.example.movie.dto.SignUpRequestDTO;
import com.example.movie.repository.UserRepository;
import com.example.movie.utility.JWTutility;

@Service
public class LoginService {
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	private JWTutility jwtutility;

	public APIResponse signUp(SignUpRequestDTO signuprequestdto) {
		APIResponse apiresponse = new APIResponse();

		//dto to entity
		User userEntity = new User();
		userEntity.setName(signuprequestdto.getName());
		userEntity.setEmailId(signuprequestdto.getEmailId());
		userEntity.setActive(Boolean.TRUE);
		userEntity.setGender(signuprequestdto.getGender());
		userEntity.setPhoneNumber(signuprequestdto.getPhoneNumber());
		userEntity.setPassword(signuprequestdto.getPassword());
		
		//store entity
		userEntity = userrepo.save(userEntity);
		
		//generate jwt
		String token1 = jwtutility.generateJwt(userEntity);
		
		Map<String, Object> data1=new HashMap<>();
		data1.put("Signup Successful and your token is", token1);
		apiresponse.setData(data1);		
		
		return apiresponse;
	}

	public APIResponse login(LoginRequestDTO loginrequestdto) {
		
		APIResponse apiresponse  = new APIResponse();
		
		//verify user id and password
		User user = userrepo.findOneByEmailIdIgnoreCaseAndPassword(loginrequestdto.getEmailId(), loginrequestdto.getPassword());
				
		//response
		if(user==null) {
			apiresponse.setData("User Login Failed...");
			return apiresponse;
		}
		
		
		//generate JWT
		String token = jwtutility.generateJwt(user);
		
		Map<String, Object> data=new HashMap<>();
		data.put("Login Successful and your access token is ", token);
		apiresponse.setData(data);
		
		
		return apiresponse;
	}

	

	
}
