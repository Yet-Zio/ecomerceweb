package com.ecomerce.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.authentication.component.AuthenticationComponent;
import com.ecomerce.authentication.models.CommonResponse;
import com.ecomerce.authentication.models.CreateUser;
import com.ecomerce.authentication.models.LoginDto;
import com.ecomerce.authentication.models.LoginResponse;
import com.ecomerce.authentication.models.SignupResponse;
import com.ecomerce.authentication.models.VerificationResponse;
import com.ecomerce.authentication.systemutils.Constants;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {
	@Autowired
	private AuthenticationComponent authcomp;

	@PostMapping("/signup")
	public ResponseEntity<CommonResponse> signup(HttpServletRequest request, @RequestBody CreateUser createuser) {
		SignupResponse response = authcomp.signup(createuser);
		if (response.isStatus())
			return new ResponseEntity<CommonResponse>(new CommonResponse(Constants.SUCESS, response), HttpStatus.OK);
		else if (response.isUserExist())
			return new ResponseEntity<CommonResponse>(new CommonResponse(response.getMessage(), Constants.FAILED),
					HttpStatus.OK);
		else
			return new ResponseEntity<CommonResponse>(new CommonResponse("oops Somethings wrong", Constants.FAILED),
					HttpStatus.OK);

	}

	@GetMapping("/signupverification")
	public ResponseEntity<CommonResponse> signupVerification(HttpServletRequest request,
			@RequestParam String authtoken) {
		VerificationResponse response = authcomp.signupVerification(authtoken);
		if (response.isStatus())
			return new ResponseEntity<CommonResponse>(new CommonResponse(Constants.SUCESS, response), HttpStatus.OK);
		else
			return new ResponseEntity<CommonResponse>(new CommonResponse("link Expired", Constants.INVALID_TOKEN),
					HttpStatus.OK);

	}

	@PostMapping("/login")
	public ResponseEntity<CommonResponse> login(HttpServletRequest request, @RequestBody LoginDto logindto) {
		LoginResponse response = authcomp.login(logindto.getEmailId(), logindto.getPassword());
		if (response.isStatus())
			return new ResponseEntity<CommonResponse>(new CommonResponse(Constants.SUCESS, response), HttpStatus.OK);
		else
			return new ResponseEntity<CommonResponse>(new CommonResponse(response.getMessage(), Constants.FAILED),
					HttpStatus.OK);
	}

}
