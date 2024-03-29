package com.nttdata.debitcard.repository.impl;

import com.nttdata.debitcard.model.DebitCard;
import com.nttdata.debitcard.repository.DebitCardReactiveMongodb;
import com.nttdata.debitcard.repository.DebitCardRepository;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class: DebitCardRepositoryImpl. <br/>
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
@Slf4j
@Repository
public class DebitCardRepositoryImpl implements DebitCardRepository {

  @Autowired
  private DebitCardReactiveMongodb debitCardReactiveMongodb;

  @Override
  public Flux<DebitCard> findDebitCards(BigInteger customerDocument) {
    return debitCardReactiveMongodb.findByCustomerDocument(customerDocument)
      .doOnComplete(() -> log.info("Successful find - customerDocument: "
        .concat(customerDocument.toString())));
  }

  @Override
  public Mono<DebitCard> findDebitCard(BigInteger cardNumber) {
    return debitCardReactiveMongodb.findByCardNumber(cardNumber)
      .doOnSuccess(result -> log.info("Successful find - cardNumber: "
        .concat(cardNumber.toString())));
  }

  @Override
  public Mono<DebitCard> findDebitCard(String debitCardId) {
    return debitCardReactiveMongodb.findById(debitCardId)
      .doOnSuccess(result -> log.info("Successful find - debitCardId: ".concat(debitCardId)));
  }

  @Override
  public Mono<Boolean> findExistsDebitCard(BigInteger cardNumber) {
    return debitCardReactiveMongodb.existsByCardNumber(cardNumber)
      .doOnSuccess(result -> log.info("Successful find exists - cardNumber: "
        .concat(cardNumber.toString())));
  }

  @Override
  public Mono<DebitCard> saveDebitCard(DebitCard debitCard) {
    return debitCardReactiveMongodb.save(debitCard)
      .doOnSuccess(result -> log.info("Successful find exists - cardNumber: "
        .concat(debitCard.getCardNumber().toString())));
  }

}
