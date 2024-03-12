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

    private final DebitCardRepository debitCardRepository;

    @Autowired
    public DebitCardServiceImpl(DebitCardRepository debitCardRepository) {
        this.debitCardRepository = debitCardRepository;
    }

    @Override
    public Mono<DebitCard> saveDebitCard(DebitCardRequest debitCardRequest) {
        return Mono.just(debitCardRequest)
            .map(debitCard -> DebitCardBuilder.toDebitCardEntity(debitCard, null))
            .flatMap(debitCardRepository::save)
            .doOnSuccess(customer -> log.info("Successful save - debitCardId: ".concat(customer.getId())));
    }

    @Override
    public Mono<DebitCard> updateDebitCard(DebitCardRequest debitCardRequest, String debitCardId) {
        return debitCardRepository.existsById(debitCardId)
            .flatMap(aBoolean -> {
                if (Boolean.TRUE.equals(aBoolean)) {
                    return debitCardRepository.save(
                        DebitCardBuilder.toDebitCardEntity(debitCardRequest, debitCardId));
                }
                return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Debit Card not found - "
                    + "debitCardId: ".concat(debitCardId)));
            })
            .doOnSuccess(account -> log.info("Successful update - debitCardId: ".concat(debitCardId)));
    }

    @Override
    public Mono<DebitCard> getDebitCard(BigInteger cardNumber) {
        return debitCardRepository.findByCardNumber(cardNumber)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Debit Card not found - "
                + "cardNumber: ".concat(cardNumber.toString()))))
            .doOnSuccess(customer -> log.info("Successful search - cardNumber: ".concat(cardNumber.toString())));
    }

    @Override
    public Flux<DebitCard> getDebitCards(String customerId) {
        return debitCardRepository.findByCustomerId(customerId)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Debit Card not found - "
                + "customerId: ".concat(customerId))))
            .doOnComplete(() -> log.info("Successful search - customerId: ".concat(customerId)));
    }

    @Override
    public Mono<Void> deleteDebitCard(String debitCardId) {
        return debitCardRepository.existsById(debitCardId)
            .flatMap(aBoolean -> {
                if (Boolean.TRUE.equals(aBoolean)) {
                    return debitCardRepository.deleteById(debitCardId);
                }
                return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Debit Card not found - " +
                    "debitCardId: ".concat(debitCardId)));
            })
            .doOnSuccess(customer -> log.info("Successful delete - debitCardId: ".concat(debitCardId)));
    }
}
