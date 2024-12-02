package com.ajinkya.authenticationApp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ajinkya.authenticationApp.util.Constants.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO {
    @NotEmpty(message = EMAIL_NONEMPTY)
    private String email;

    @NotEmpty(message = PASSWORD_NONEMPTY)
    private String password;

    @NotEmpty(message = NAME_NONEMPTY)
    private String name;
}
