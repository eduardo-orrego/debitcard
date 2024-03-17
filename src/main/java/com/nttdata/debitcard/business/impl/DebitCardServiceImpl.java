package com.nttdata.debitcard.business.impl;

import com.nttdata.debitcard.api.request.DebitCardRequest;
import com.nttdata.debitcard.builder.DebitCardBuilder;
import com.nttdata.debitcard.business.DebitCardService;
import com.nttdata.debitcard.model.DebitCard;
import com.nttdata.debitcard.repository.DebitCardRepository;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class DebitCardServiceImpl implements DebitCardService {

    @Autowired
    private DebitCardRepository debitCardRepository;

    @Override
    public Mono<DebitCard> saveDebitCard(DebitCardRequest debitCardRequest) {

        return Mono.just(debitCardRequest)
            .map(DebitCardBuilder::toDebitCardEntity)
            .flatMap(debitCardRepository::saveDebitCard)
            .doOnSuccess(customer -> log.info("Successful save - debitCardId: ".concat(customer.getId())));
    }

    @Override
    public Mono<DebitCard> updateDebitCard(DebitCardRequest debitCardRequest, String debitCardId) {
        return debitCardRepository.findDebitCard(debitCardId)
            .flatMap(debitCard -> debitCardRepository.saveDebitCard(
                DebitCardBuilder.toDebitCardEntity(debitCardRequest, debitCard))
            )
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Debit Card not found - "
                + "debitCardId: ".concat(debitCardId))));

    }

    @Override
    public Mono<DebitCard> getDebitCard(BigInteger cardNumber) {
        return debitCardRepository.findDebitCard(cardNumber)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Debit Card not found - "
                + "cardNumber: ".concat(cardNumber.toString()))));

    }

    @Override
    public Flux<DebitCard> getDebitCards(String customerId) {
        return debitCardRepository.findDebitCards(customerId)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Debit Card not found - "
                + "customerId: ".concat(customerId))));

    }

}
