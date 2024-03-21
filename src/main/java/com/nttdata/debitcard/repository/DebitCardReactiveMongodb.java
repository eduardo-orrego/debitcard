package com.nttdata.debitcard.repository;

import com.nttdata.debitcard.model.DebitCard;
import java.math.BigInteger;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class: DebitCardReactiveMongodb. <br/>
 * <b>Bootcamp NTTDATA</b><br/>
 *
 * @author NTTDATA
 * @version 1.0
 *   <u>Developed by</u>:
 *   <ul>
 *   <li>Developer Carlos</li>
 *   </ul>
 * @since 1.0
 */
@Repository
public interface DebitCardReactiveMongodb extends ReactiveMongoRepository<DebitCard, String> {

  Mono<DebitCard> findByCardNumber(BigInteger cardNumber);

  Flux<DebitCard> findByCustomerDocument(BigInteger customerDocument);

  Mono<Boolean> existsByCardNumber(BigInteger cardNumber);

}
