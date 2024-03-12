package com.nttdata.debitcard.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "creditCard")
public class DebitCard {

    @Id
    private String id;
    private String cardNumber;
    private String status;
    private LocalDate expirationDate;
    private LocalDate activateDate;
    private String cvv;
    private String customerId;
    private BigDecimal availableBalance;
    private String accountId;
    private List<AccountAssociated> accountsAssociated;
    private LocalDateTime lastTransactionDate;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;
}
