package com.nttdata.debitcard.model;

import java.math.BigInteger;
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
@Document(collection = "debitCard")
public class DebitCard {

    @Id
    private String id;
    private BigInteger cardNumber;
    private String status;
    private LocalDate expirationDate;
    private LocalDate activateDate;
    private Integer cvv;
    private String customerId;
    private List<AccountAssociated> accountsAssociated;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;
}
