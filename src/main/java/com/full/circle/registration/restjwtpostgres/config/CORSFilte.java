package com.full.circle.registration.restjwtpostgres.config;

import com.full.circle.registration.restjwtpostgres.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CORSFilte implements javax.servlet.Filter{

    private static Logger logger = LoggerFactory.getLogger(CORSFilte.class);

    @Autowired
    Constants constants;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String origin = request.getHeader("Origin") == null ? "*" : request.getHeader("Origin");

        logger.info(String.format("Cors filter Origin: %s", origin));

        List<String> domains = new ArrayList<>();
        if(constants != null){
            domains.add(constants.urlWebsocket);
            domains.add(constants.urlFront);
        }
        List<String> allowedOrigins = new ArrayList<>(Arrays.asList("http://localhost:3000"));
        allowedOrigins.addAll(domains);

        response.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, HEAD, PUT, GET, OPTIONS, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        chain.doFilter(req, res);
    }
}
