package com.evg.billing.dto.response;

import com.evg.billing.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse extends BaseResponse {

    private Long userId;
    private Long accountId;
    private BigDecimal currentAmount;

}
