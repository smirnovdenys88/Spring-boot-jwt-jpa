package com.full.circle.registration.restjwtpostgres.utils;

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

    public static final String EMAIL_USER = "dsmirnov@s-pro.io";
    public static final String EMAIL_PASS = "spronarada";
    public static final String EMAIL_HOST = "smtp.gmail.com";
    public static final String EMAIL_PORT = "587";

    public static final String baseURL = "http://localhost:8080";
    public static final String pathConfirmEmail = "authentication/register/confirm/";
    public static final String URL_SIGN_IN_PAGE = "http://localhost:8080/authentication/authenticate";

    @Value("${prop.url.base.front}")
    public String urlFront;

    @Value("${prop.url.websocket}")
    public String urlWebsocket;

}
