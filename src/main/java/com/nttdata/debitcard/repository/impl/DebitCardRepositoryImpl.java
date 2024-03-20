package com.nttdata.debitcard.repository.impl;

import com.nttdata.debitcard.model.DebitCard;
import com.nttdata.debitcard.repository.DebitCardReactiveMongodb;
import com.nttdata.debitcard.repository.DebitCardRepository;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class DebitCardRepositoryImpl implements DebitCardRepository {

    @Autowired
    private DebitCardReactiveMongodb debitCardReactiveMongodb;

    @Override
    public Flux<DebitCard> findDebitCards(BigInteger customerDocument) {
        return debitCardReactiveMongodb.findByCustomerDocument(customerDocument)
            .doOnComplete(() -> log.info("Successful find - customerDocument: ".concat(customerDocument.toString())));
    }

    @Override
    public Mono<DebitCard> findDebitCard(BigInteger cardNumber) {
        return debitCardReactiveMongodb.findByCardNumber(cardNumber)
            .doOnSuccess(customer -> log.info("Successful find - cardNumber: ".concat(cardNumber.toString())));
    }

    @Override
    public Mono<DebitCard> findDebitCard(String debitCardId) {
        return debitCardReactiveMongodb.findById(debitCardId)
            .doOnSuccess(account -> log.info("Successful find - debitCardId: ".concat(debitCardId)));
    }

    @Override
    public Mono<Boolean> findExistsDebitCard(BigInteger cardNumber) {
        return debitCardReactiveMongodb.existsByCardNumber(cardNumber)
            .doOnSuccess(result -> log.info("Successful find exists - cardNumber: ".concat(cardNumber.toString())));
    }

    @Override
    public Mono<DebitCard> saveDebitCard(DebitCard debitCard) {
        return debitCardReactiveMongodb.save(debitCard)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Debit Card not found - "
                + "debitCardId: ".concat(debitCard.getId()))));
    }

}
