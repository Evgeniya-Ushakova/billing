package com.evg.billing.repository;

import com.evg.billing.entity.Account;
import com.evg.billing.enums.ErrorMessageCode;
import com.evg.billing.exception.DataNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUserId(Long userId);

    default Account findByUserIdOrElseThrow(Long userId) {
        return findByUserId(userId).orElseThrow(() -> new DataNotFoundException(ErrorMessageCode.DATA_NOT_FOUND.getCode(),
                String.format("Account for userId = %s not found", userId)));
    }

}
