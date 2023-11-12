package com.evg.billing.service;

public interface AuthService {

    void checkAuth(String authToken, Long userIdFromRequest);

}
