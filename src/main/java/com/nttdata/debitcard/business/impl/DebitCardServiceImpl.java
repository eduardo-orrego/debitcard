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

        return debitCardRepository.findExistsDebitCard(debitCardRequest.getCardNumber())
            .flatMap(aBoolean -> {
                if (Boolean.FALSE.equals(aBoolean)) {
                    return debitCardRepository.saveDebitCard(DebitCardBuilder.toDebitCardEntity(debitCardRequest));

                }
                return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "There is another Debit Card with the same Card Number: "
                        .concat(debitCardRequest.getCardNumber().toString())));
            });

    }

    @Override
    public Mono<DebitCard> updateDebitCard(DebitCardRequest debitCardRequest, String debitCardId) {
        return debitCardRepository.findDebitCard(debitCardId)
            .flatMap(debitCardCurrent -> {
                if (debitCardRequest.getCardNumber().compareTo(debitCardCurrent.getCardNumber()) == 0) {
                    return debitCardRepository.saveDebitCard(DebitCardBuilder.toDebitCardEntity(debitCardRequest,
                        debitCardCurrent));
                }

                return this.saveDebitCard(debitCardRequest);
            })
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Debit Card not found - debitCardId: ".concat(debitCardId)))));
    }

    @Override
    public Mono<DebitCard> getDebitCard(BigInteger cardNumber) {
        return debitCardRepository.findDebitCard(cardNumber)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Debit Card not found - "
                + "cardNumber: ".concat(cardNumber.toString()))));
    }

    @Override
    public Flux<DebitCard> getDebitCards(BigInteger customerDocument) {
        return debitCardRepository.findDebitCards(customerDocument)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Debit Card not found - "
                + "customerDocument: ".concat(customerDocument.toString()))));

    }

}
