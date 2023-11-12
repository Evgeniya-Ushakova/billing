package com.evg.billing.service;

import com.evg.billing.dto.requets.AccountUpdateRequest;
import com.evg.billing.dto.response.AccountResponse;

public interface AccountService {

    AccountResponse createAccount(Long userId);

    AccountResponse update(AccountUpdateRequest request, Long userId);

    AccountResponse getAccount(Long userId);
}
