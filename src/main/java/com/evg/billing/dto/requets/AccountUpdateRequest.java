package com.evg.billing.dto.requets;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateRequest {

    private BigDecimal amountToIncrease;
    private BigDecimal amountToDecrease;

}
