package com.nttdata.debitcard.business;

import com.nttdata.debitcard.api.request.DebitCardRequest;
import com.nttdata.debitcard.model.DebitCard;
import java.math.BigInteger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class: DebitCardService. <br/>
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
public interface DebitCardService {
  Mono<DebitCard> saveDebitCard(DebitCardRequest debitCardRequest);

  Mono<DebitCard> updateDebitCard(DebitCardRequest debitCardRequest, String debitCardId);

  Mono<DebitCard> getDebitCard(BigInteger cardNumber);

  Flux<DebitCard> getDebitCards(BigInteger customerDocument);
}
