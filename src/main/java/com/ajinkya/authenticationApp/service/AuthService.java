package com.ajinkya.authenticationApp.service;

import com.ajinkya.authenticationApp.Validator.AuthValidator;
import com.ajinkya.authenticationApp.dto.SignInDTO;
import com.ajinkya.authenticationApp.dto.SignupDTO;
import com.ajinkya.authenticationApp.dto.TokenDTO;
import com.ajinkya.authenticationApp.entity.User;
import com.ajinkya.authenticationApp.repository.UserRepository;
import com.ajinkya.authenticationApp.util.CustomPasswordEncoder;
import com.ajinkya.authenticationApp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

import static com.ajinkya.authenticationApp.util.Constants.*;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthValidator authValidator;

    @Autowired
    public AuthService(UserRepository userRepository, AuthValidator authValidator) {
        this.authValidator = authValidator;
        this.userRepository = userRepository;
    }

    private CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();

    public TokenDTO signUp(SignupDTO signupDTO) {
        List<String> errors = authValidator.validate(signupDTO);
        if (!errors.isEmpty()) {
            return TokenDTO.builder().token("").message(errors.toString()).httpCode(BAD_REQUEST).build();
        }
        String email = signupDTO.getEmail();
        String password = signupDTO.getPassword();
        String name = signupDTO.getName();
        if (!checkEmailFormat(email)) {
            return TokenDTO.builder().token("").message(INCORRECT_MAIL_FORMAT).httpCode(BAD_REQUEST).build();
        }
        if (userRepository.findByEmail(email) != null) {
            return TokenDTO.builder().token("").message(USER_EXISTS).httpCode(CONFLICT).build();
        }

        User user = User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
        userRepository.save(user);

        String token = JwtUtil.generateToken(user.getEmail());

        return TokenDTO.builder().token(token).httpCode(CREATED).message("Token created successfully").build();
    }

    private boolean checkEmailFormat(String email) {

        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public TokenDTO signIn(SignInDTO signInDTO) {
        List<String> errors = authValidator.validate(signInDTO);
        if (!errors.isEmpty()) {
            return TokenDTO.builder().token("").message(errors.toString()).httpCode(CONFLICT).build();
        }
        String email = signInDTO.getEmail();
        String password = signInDTO.getPassword();
        User user = userRepository.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return TokenDTO.builder().token(" ").httpCode(INVALID).message(INVALID_CREDS).build();
        }

        String token = JwtUtil.generateToken(user.getEmail());

        return TokenDTO.builder().token(token).httpCode(OK).message(TOKEN_SUCCESS).build();
    }

    public TokenDTO generateToken(TokenDTO tokenDTO) {
        String email = "";
        try {
            email = JwtUtil.getEmail(tokenDTO.getToken());
        } catch (Exception e) {
            return TokenDTO.builder().httpCode(500).build();
        }
        String token = JwtUtil.generateToken(email);
        return TokenDTO.builder().token(token).httpCode(200).build();
    }

}
