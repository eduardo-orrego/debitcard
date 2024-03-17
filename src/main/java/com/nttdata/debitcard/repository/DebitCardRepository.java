package com.nttdata.debitcard.repository;

import com.nttdata.debitcard.model.DebitCard;
import java.math.BigInteger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardRepository {
    Flux<DebitCard> findDebitCards(BigInteger customerDocument);

    Mono<DebitCard> findDebitCard(BigInteger cardNumber);

    Mono<DebitCard> findDebitCard(String debitCardId);

    Mono<Boolean> findExistsDebitCard(BigInteger customerDocument);

    Mono<DebitCard> saveDebitCard(DebitCard debitCard);
}
