package com.evg.billing.service.impl;

import com.evg.billing.service.user.UserClient;
import com.evg.billing.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@Slf4j(topic = "AUTH_SERVICE")
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    public static String USER_TOKEN;
    private final UserClient userClient;


    @Override
    public void checkAuth(String authToken, Long userIdFromRequest) {

        if(StringUtils.isBlank(authToken)) {
            throw new AuthenticationCredentialsNotFoundException("Credentials empty");
        }

        String emailFromToken = authToken.split(":")[0];
        Long userIdFromToken = Long.valueOf(authToken.split(":")[1]);

        if (!Objects.equals(userIdFromToken, userIdFromRequest)) {
            throw new BadCredentialsException("This is not your profile data. Forbidden!");
        }

        userClient.getUser(userIdFromToken, authToken);

        USER_TOKEN = authToken;

    }
}
