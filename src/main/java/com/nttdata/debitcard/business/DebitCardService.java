package com.nttdata.debitcard.business;

import com.nttdata.debitcard.api.request.DebitCardRequest;
import com.nttdata.debitcard.model.DebitCard;
import java.math.BigInteger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardService {
    Mono<DebitCard> saveDebitCard(DebitCardRequest debitCardRequest);

    Mono<DebitCard> updateDebitCard(DebitCardRequest debitCardRequest, String debitCardId);

    Mono<DebitCard> getDebitCard(BigInteger cardNumber);

    Flux<DebitCard> getDebitCards(String customerId);
}
