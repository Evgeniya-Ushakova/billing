package com.evg.billing.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(schema = "evg", name = "account")
@EqualsAndHashCode(callSuper = true)
public class Account extends EntityBase<Long> {

    @Column(name = "AMOUNT", columnDefinition = "decimal")
    private BigDecimal amount;
    @Column(name = "USER_ID", columnDefinition = "BIGINT")
    private Long userId;

}