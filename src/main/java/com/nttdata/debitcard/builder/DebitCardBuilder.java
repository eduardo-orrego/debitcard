package com.nttdata.debitcard.builder;

import com.nttdata.debitcard.api.request.AccountAssociatedRequest;
import com.nttdata.debitcard.api.request.DebitCardRequest;
import com.nttdata.debitcard.model.AccountAssociated;
import com.nttdata.debitcard.model.DebitCard;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class DebitCardBuilder {
    DebitCardBuilder() {
    }

    public static DebitCard toDebitCardEntity(DebitCardRequest debitCardRequest) {
        return DebitCard.builder()
            .status(debitCardRequest.getStatus().name())
            .customerId(debitCardRequest.getCustomerId())
            .accountsAssociated(toAccountAssociatedEntityList(debitCardRequest.getAccountsAssociated(), null))
            .expirationDate(Objects.nonNull(debitCardRequest.getExpirationDate())
                ? debitCardRequest.getExpirationDate() : LocalDate.now().plusYears(1))
            .activateDate(Objects.nonNull(debitCardRequest.getExpirationDate())
                ? debitCardRequest.getActivateDate() : LocalDate.now())
            .cvv(generateCvv())
            .cardNumber(generateCardNumber())
            .dateCreated(LocalDateTime.now())
            .lastUpdated(LocalDateTime.now())
            .build();
    }

    public static DebitCard toDebitCardEntity(DebitCardRequest debitCardRequest, DebitCard debitCard) {
        return DebitCard.builder()
            .id(debitCard.getId())
            .status(debitCardRequest.getStatus().name())
            .cardNumber(Objects.nonNull(debitCardRequest.getCardNumber())
                ? debitCardRequest.getCardNumber() : debitCard.getCardNumber())
            .cvv(Objects.nonNull(debitCardRequest.getCvv())
                ? debitCardRequest.getCvv() : debitCard.getCvv())
            .expirationDate(Objects.nonNull(debitCardRequest.getExpirationDate())
                ? debitCardRequest.getExpirationDate() : debitCard.getExpirationDate())
            .activateDate(Objects.nonNull(debitCardRequest.getActivateDate())
                ? debitCardRequest.getActivateDate() : debitCard.getActivateDate())
            .customerId(debitCardRequest.getCustomerId())
            .accountsAssociated(toAccountAssociatedEntityList(debitCardRequest.getAccountsAssociated(),
                debitCard.getAccountsAssociated()))
            .dateCreated(debitCard.getDateCreated())
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
        List<AccountAssociatedRequest> accountAssociatedRequestList,
        List<AccountAssociated> accountAssociatedList) {
        return Objects.nonNull(accountAssociatedRequestList) && !accountAssociatedRequestList.isEmpty()
            ? accountAssociatedRequestList.stream().map(DebitCardBuilder::toAccountAssociatedEntity).toList()
            : accountAssociatedList;
    }

    private static BigInteger generateCardNumber() {
        String cardNumber = "20".concat(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        return new BigInteger(cardNumber);
    }

    private static Integer generateCvv() {
        return ThreadLocalRandom.current().nextInt(100, 1000);
    }
}
