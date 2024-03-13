package com.nttdata.debitcard.builder;

import com.nttdata.debitcard.api.request.AccountAssociatedRequest;
import com.nttdata.debitcard.api.request.DebitCardRequest;
import com.nttdata.debitcard.model.AccountAssociated;
import com.nttdata.debitcard.model.DebitCard;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class DebitCardBuilder {
    DebitCardBuilder() {
    }

    public static DebitCard toDebitCardEntity(DebitCardRequest debitCardRequest, String debitCardId) {
        return DebitCard.builder()
            .id(debitCardId)
            .cardNumber(Objects.nonNull(debitCardRequest.getCardNumber())
                ? debitCardRequest.getCardNumber()
                : generateCardNumber())
            .status(debitCardRequest.getStatus().name())
            .expirationDate(debitCardRequest.getExpirationDate())
            .activateDate(debitCardRequest.getActivateDate())
            .cvv(debitCardRequest.getCvv())
            .customerId(debitCardRequest.getCustomerId())
            .accountsAssociated(toAccountAssociatedEntityList(debitCardRequest.getAccountsAssociated()))
            .lastTransactionDate(LocalDateTime.now())
            .dateCreated(LocalDateTime.now())
            .lastUpdated(LocalDateTime.now())
            .build();
    }

    private static AccountAssociated toAccountAssociatedEntity(AccountAssociatedRequest accountAssociatedRequest) {
        return AccountAssociated.builder()
            .associatedType(accountAssociatedRequest.getAssociatedType().name())
            .accountId(accountAssociatedRequest.getAccountId())
            .build();
    }

    private static List<AccountAssociated> toAccountAssociatedEntityList(
        List<AccountAssociatedRequest> accountAssociatedRequestList) {
        return Objects.nonNull(accountAssociatedRequestList) && !accountAssociatedRequestList.isEmpty()
            ? accountAssociatedRequestList.stream().map(DebitCardBuilder::toAccountAssociatedEntity).toList()
            : null;
    }

    private static BigInteger generateCardNumber() {
        String cardNumber = "20".concat(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        return new BigInteger(cardNumber);
    }
}
