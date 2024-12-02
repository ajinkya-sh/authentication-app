package com.ajinkya.authenticationApp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ajinkya.authenticationApp.util.Constants.EMAIL_NONEMPTY;
import static com.ajinkya.authenticationApp.util.Constants.PASSWORD_NONEMPTY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInDTO {
    @NotEmpty(message = EMAIL_NONEMPTY)
    private String email;
    @NotEmpty(message = PASSWORD_NONEMPTY)
    private String password;
}
