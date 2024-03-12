package com.nttdata.debitcard.api.request;

import com.nttdata.debitcard.enums.DebitCardStatusEnum;
import com.nttdata.debitcard.model.AccountAssociated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitCardRequest {

    @NotBlank(message = "El campo 'cardNumber' no puede ser vacío")
    private String cardNumber;
    @NotNull(message = "El campo 'status' no puede ser nulo")
    private DebitCardStatusEnum status;
    private LocalDate expirationDate;
    private LocalDate activateDate;
    private String cvv;
    @NotBlank(message = "El campo 'customerId' no puede ser vacío")
    private String customerId;
    @NotNull(message = "El campo 'availableBalance' no puede ser vacío")
    private BigDecimal availableBalance;
    @NotBlank(message = "El campo 'accountId' no puede ser vacío")
    private String accountId;
    private List<AccountAssociatedRequest> accountsAssociated;
}
