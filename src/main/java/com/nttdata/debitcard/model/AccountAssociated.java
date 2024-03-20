package com.nttdata.debitcard.model;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountAssociated {

    private String associatedType;
    private BigInteger accountNumber;

}
