package com.nttdata.debitcard.repository;

import com.nttdata.debitcard.model.DebitCard;
import java.math.BigInteger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class: DebitCardRepository. <br/>
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
public interface DebitCardRepository {
  Flux<DebitCard> findDebitCards(BigInteger customerDocument);

  Mono<DebitCard> findDebitCard(BigInteger cardNumber);

  Mono<DebitCard> findDebitCard(String debitCardId);

  Mono<Boolean> findExistsDebitCard(BigInteger cardNumber);

  Mono<DebitCard> saveDebitCard(DebitCard debitCard);
}
