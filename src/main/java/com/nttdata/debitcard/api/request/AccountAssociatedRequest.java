package com.nttdata.debitcard.api.request;

import com.nttdata.debitcard.enums.AssociatedTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountAssociatedRequest {

    @NotNull
    private AssociatedTypeEnum associatedType;

    @NotBlank
    private BigInteger accountNumber;

}
