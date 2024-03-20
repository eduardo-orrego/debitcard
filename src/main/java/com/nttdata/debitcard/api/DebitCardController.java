package com.nttdata.debitcard.api;

import com.nttdata.debitcard.api.request.DebitCardRequest;
import com.nttdata.debitcard.business.DebitCardService;
import com.nttdata.debitcard.model.DebitCard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotNull;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/debitCards")
public class DebitCardController {
    private final DebitCardService debitCardService;

    @Autowired
    DebitCardController(DebitCardService debitCardService) {
        this.debitCardService = debitCardService;
    }

    /**
     * POST : Create a new Debit Card
     *
     * @param debitCard (required)
     * @return Created (status code 201)
     */
    @Operation(
        operationId = "debitCardPost",
        summary = "Create a new Debit Card",
        responses = {
            @ApiResponse(responseCode = "201", description = "Created", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = DebitCard.class))
            })
        }
    )
    @PostMapping(
        value = "",
        produces = {"application/json"},
        consumes = {"application/json"}
    )
    public Mono<DebitCard> debitCardPost(
        @Validated @RequestBody(required = false) DebitCardRequest debitCard
    ) {
        return debitCardService.saveDebitCard(debitCard);
    }

    /**
     * PUT : Update a Debit Card exists
     *
     * @param debitCardId (required)
     * @param debitCard   (required)
     * @return Ok (status code 200)
     */
    @Operation(
        operationId = "debitCardPut",
        summary = "Update a Debit Card",
        responses = {
            @ApiResponse(responseCode = "200", description = "Updated", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = DebitCard.class))
            })
        }
    )
    @PutMapping(
        value = "/{debitCardId}",
        produces = {"application/json"},
        consumes = {"application/json"}
    )
    public Mono<DebitCard> debitCardPut(
        @Parameter(name = "debitCardId", description = "", required = true, in = ParameterIn.PATH)
        @PathVariable("debitCardId") String debitCardId,
        @Validated @RequestBody DebitCardRequest debitCard
    ) {
        return debitCardService.updateDebitCard(debitCard, debitCardId);
    }

    /**
     * GET /{cardNumber} : Get information about a specific Debit Card
     *
     * @param cardNumber (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "debitCardGet",
        summary = "Get information about a specific Debit Card",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = DebitCard.class))
            })
        }
    )
    @GetMapping(
        value = "/{cardNumber}",
        produces = {"application/json"}
    )
    public Mono<DebitCard> debitCardGet(
        @Parameter(name = "cardNumber", description = "", required = true, in = ParameterIn.PATH)
        @PathVariable("cardNumber") BigInteger cardNumber
    ) {
        return debitCardService.getDebitCard(cardNumber);
    }


    /**
     * GET : Get a list of Debit Cards for the customer
     *
     * @param customerDocument (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "debitCardsGet",
        summary = "Get a list of Debit Cards for the customer",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation =
                    DebitCard.class)))
            })
        }
    )
    @GetMapping(
        value = "",
        produces = {"application/json"}
    )
    public Flux<DebitCard> debitCardsGet(
        @NotNull @Parameter(name = "customerDocument", description = "", required = true, in = ParameterIn.QUERY)
        @Validated @RequestParam(value = "customerDocument") BigInteger customerDocument
    ) {
        return debitCardService.getDebitCards(customerDocument);
    }

}
