package com.nttdata.debitcard.enums;

import lombok.Getter;

@Getter
public enum DebitCardStatusEnum {
    ACTIVE,
    INACTIVE,
    BLOCKED,
    CANCELLED,
    EXPIRED
}
