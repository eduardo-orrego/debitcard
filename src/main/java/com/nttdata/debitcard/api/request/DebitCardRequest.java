package com.nttdata.debitcard.api.request;

import com.nttdata.debitcard.enums.DebitCardStatusEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitCardRequest {

    @NotNull(message = "El campo 'status' no puede ser nulo")
    private DebitCardStatusEnum status;
    @NotBlank(message = "El campo 'customerId' no puede ser vacío")
    private String customerId;
    @NotEmpty(message = "El campo 'accountsAssociated' no puede ser vacío")
    @Valid
    private List<AccountAssociatedRequest> accountsAssociated;

    private LocalDate expirationDate;
    private LocalDate activateDate;
    private Integer cvv;
    private BigInteger cardNumber;
}
