package com.theironyard.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vasantia on 8/10/16.
 */

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Login failed")
public class LoginFailedException extends RuntimeException {
}
