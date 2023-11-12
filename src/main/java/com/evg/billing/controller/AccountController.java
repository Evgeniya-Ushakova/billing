package com.evg.billing.controller;

import com.evg.billing.dto.requets.AccountUpdateRequest;
import com.evg.billing.dto.response.AccountResponse;
import com.evg.billing.service.AccountService;
import com.evg.billing.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/billing/account")
public class AccountController {

    private final static String  CREATE_ACCOUNT = "/create/{userId}";
    private final static String TOP_UP_ACCOUNT = "/top-up/{userId}";
    private final static String GET_ACCOUNT = "/{userId}";

    private final AccountService accountService;
    private final AuthService authService;

    @PostMapping(CREATE_ACCOUNT)
    public AccountResponse createAccount(@PathVariable Long userId) {
        return accountService.createAccount(userId);
    }

    @PutMapping(TOP_UP_ACCOUNT)
    public AccountResponse topUpAccount(@PathVariable Long userId,
                                        @RequestBody AccountUpdateRequest request,
                                        @RequestHeader("x-auth-token") String authToken) {
        authService.checkAuth(authToken, userId);
        return accountService.update(request, userId);
    }

    @GetMapping(GET_ACCOUNT)
    public AccountResponse getAccount(@PathVariable Long userId,
                                      @RequestHeader("x-auth-token") String authToken) {
        authService.checkAuth(authToken, userId);
        return accountService.getAccount(userId);
    }

}
