package com.ajinkya.authenticationApp.controller;

import com.ajinkya.authenticationApp.dto.SignInDTO;
import com.ajinkya.authenticationApp.dto.SignupDTO;
import com.ajinkya.authenticationApp.dto.TokenDTO;
import com.ajinkya.authenticationApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<TokenDTO> signUp(@RequestBody SignupDTO signupDTO) {
        TokenDTO token = authService.signUp(signupDTO);
        return new ResponseEntity<>(token, HttpStatusCode.valueOf(token.getHttpCode()));
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenDTO> signIn(@RequestBody SignInDTO signInDTO) {
        TokenDTO token = authService.signIn(signInDTO);
        return new ResponseEntity<>(token, HttpStatusCode.valueOf(token.getHttpCode()));
    }

    @PostMapping("/generateToken")
    public ResponseEntity<TokenDTO> generateToken(@RequestBody TokenDTO tokenDTO) {
        TokenDTO token = authService.generateToken(tokenDTO);
        return new ResponseEntity<>(token, HttpStatusCode.valueOf(token.getHttpCode()));
    }

}
