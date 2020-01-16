package com.start.kit.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Constants {
    public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60;
    public static final String SIGNING_KEY = "D87B6573EA18E86A66178BEF42EA2";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static final String PATH_CONFIRM_EMAIL = "authentication/register/confirm/";
    public static final String URL_SIGN_IN_PAGE = "http://localhost:8080/authentication/authenticate";

    //localhost:8080
    @Value("#{systemEnvironment['BASE_URL']}")
    public String BASE_URL;

    //localhost:8080
    @Value("#{systemEnvironment['URL_FRONT']}")
    public String URL_FRONT;

    @Value("#{systemEnvironment['EMAIL_USER']}")
    public String EMAIL_USER;

    @Value("#{systemEnvironment['EMAIL_PASS']}")
    public String EMAIL_PASS;

    //smtp.gmail.com
    @Value("#{systemEnvironment['EMAIL_HOST']}")
    public String EMAIL_HOST;

    //587
    @Value("#{systemEnvironment['EMAIL_PORT']}")
    public String EMAIL_PORT;
}
