package com.evg.billing.service.impl;

import com.evg.billing.dto.requets.AccountUpdateRequest;
import com.evg.billing.dto.response.AccountResponse;
import com.evg.billing.entity.Account;
import com.evg.billing.enums.ErrorMessageCode;
import com.evg.billing.exception.BadRequestException;
import com.evg.billing.repository.AccountRepository;
import com.evg.billing.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@Slf4j(topic = "ACCOUNT_SERVICE")
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public AccountResponse createAccount(Long userId) {
        Account account = new Account();
        account.setUserId(userId);
        account = accountRepository.save(account);

        return AccountResponse.builder()
                .userId(userId)
                .accountId(account.getId())
                .currentAmount(account.getAmount())
                .build();
    }

    @Override
    @Transactional
    public AccountResponse update(AccountUpdateRequest request, Long userId) {
        Account account = accountRepository.findByUserIdOrElseThrow(userId);

        if(Objects.nonNull(request.getAmountToIncrease())) {
            BigDecimal newAmount = account.getAmount().add(request.getAmountToIncrease());
            account.setAmount(newAmount);
            LOGGER.info("Amount increase for userId = {}, increaseSum = {}", userId, request.getAmountToIncrease());
            return AccountResponse.builder()
                    .accountId(account.getId())
                    .userId(account.getUserId())
                    .currentAmount(account.getAmount())
                    .build();
        }

        if(Objects.nonNull(request.getAmountToDecrease())) {
            validateFotDecrease(account, request.getAmountToDecrease());
            BigDecimal newAmount = account.getAmount().subtract(request.getAmountToDecrease());
            account.setAmount(newAmount);
            LOGGER.info("Amount decrease for userId = {}, decreaseSum = {}", userId, request.getAmountToDecrease());
        }

        return AccountResponse.builder()
                .accountId(account.getId())
                .userId(account.getUserId())
                .currentAmount(account.getAmount())
                .build();
    }

    @Override
    public AccountResponse getAccount(Long userId) {
        Account account = accountRepository.findByUserIdOrElseThrow(userId);
        return AccountResponse.builder()
                .accountId(account.getId())
                .userId(account.getUserId())
                .currentAmount(account.getAmount())
                .build();
    }

    private void validateFotDecrease(Account account, BigDecimal amountToDecrease) {
        if (account.getAmount().compareTo(amountToDecrease) < 0) {
            throw new BadRequestException(ErrorMessageCode.BAD_REQUEST.getCode(),
                    String.format("Not enough amount to decrease. AccountId = %s, currentAmount = %s, amount to descrease = %s",
                            account.getId(),
                            account.getAmount(),
                            amountToDecrease));
        }
    }



}
