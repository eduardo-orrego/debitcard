package com.nttdata.debitcard.repository;

import com.nttdata.debitcard.model.DebitCard;
import java.math.BigInteger;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DebitCardRepository extends ReactiveMongoRepository<DebitCard, String> {

    Mono<DebitCard> findByCardNumber(BigInteger cardNumber);

    Flux<DebitCard> findByCustomerId(String customerId);

}
