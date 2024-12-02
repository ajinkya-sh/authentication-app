package com.ajinkya.authenticationApp.util;

public class Constants {

    public final static String INCORRECT_MAIL_FORMAT = "Incorrect email format";

    public final static String USER_EXISTS = "User already exist";

    public final static String INVALID_CREDS = "Invalid email or password";

    public final static String TOKEN_SUCCESS = "Token created successfully";

    public final static String EMAIL_NONEMPTY = "email can not be empty";

    public final static String PASSWORD_NONEMPTY = "password can not be empty";

    public final static String NAME_NONEMPTY = "name can not be empty";

    public final static String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,7}$";

    public final static int CREATED = 201;

    public final static int OK = 200;

    public final static int CONFLICT = 409;

    public final static int BAD_REQUEST = 400;

    public final static int INVALID = 403;

}
