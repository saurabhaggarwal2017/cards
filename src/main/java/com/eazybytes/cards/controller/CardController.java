package com.eazybytes.cards.controller;

import com.eazybytes.cards.custom_annotation.ValidCardNumber;
import com.eazybytes.cards.dto.*;
import com.eazybytes.cards.service.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Card Controller.",
        description = "Crud Api for Card microservice like CreateCard, GetAllCardsDetails, GetSingleCardDetails, UpdateCardDetails, DeleteCard."
)
@RestController
@RequestMapping(path = "/api/v1/cards", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RefreshScope
public class CardController {
    private final ICardService cardService;
    private Environment environment;
    @Autowired
    private CardsContactInfoDto cardsContactInfoDto;
    @Value("${build.version}")
    private String version;
    public CardController(ICardService cardService) {
        this.cardService = cardService;
    }


    @Operation(
            method = "POST",
            description = "Create card for user using mobile number.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Http Status Created"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Http Status Bad Request",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Http Status Internal Server Error.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }

    )
    @PostMapping
    public ResponseEntity<ResponseDto> createCard(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
            @RequestParam("mobileNumber") String mobileNumber,
            @Size(min = 3, max = 14, message = "card owner name should be in 3 to 14 words.")
            @RequestParam("cardOwnerName") String cardOwnerName) {
        cardService.createCard(mobileNumber, cardOwnerName);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.toString(), "Card successfully created."));
    }

    @Operation(
            method = "GET",
            description = "Fetch all cards which are link to your mobile number.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Http Status OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Http Status Bad Request",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Http Status Resource not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Http Status Internal Server Error.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }

    )
    @GetMapping("/all")
    public ResponseEntity<List<CardDto>> getAllCardsDetails(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
            @RequestParam("mobileNumber") String mobileNumber) {
        List<CardDto> allCards = cardService.getAllCardDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(allCards);
    }

    @Operation(
            method = "GET",
            description = "Fetch card details using your Mobile number and Card Number.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Http Status OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Http Status Bad Request",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Http Status Resource not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Http Status Internal Server Error.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }

    )
    @GetMapping
    public ResponseEntity<CardDto> getCardDetails(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
            @RequestParam("mobileNumber") String mobileNumber,
            @ValidCardNumber
            @RequestParam("cardNumber") long cardNumber
    ) {
        CardDto cardDto = cardService.getCardDetails(mobileNumber, cardNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardDto);
    }

    @Operation(
            method = "PUT",
            description = "Update card details using your Mobile number and Card Number.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Http Status OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Http Status Bad Request",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Http Status Resource not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Http Status Internal Server Error.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }

    )
    @PutMapping
    public ResponseEntity<ResponseDto> updateCardDetails(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
            @RequestParam("mobileNumber") String mobileNumber,
            @ValidCardNumber
            @RequestParam("cardNumber") long cardNumber,
            @Valid @RequestBody CardDetailsUpdateDto updateDto
    ) {
        cardService.updateCardDetails(mobileNumber, cardNumber, updateDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(), "Card details update Successfully."));

    }

    @Operation(
            method = "DElETE",
            description = "Delete card details using your Mobile number and Card Number.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Http Status OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Http Status Bad Request",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Http Status Resource not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Http Status Internal Server Error.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }

    )
    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteCard(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
            @RequestParam("mobileNumber") String mobileNumber,
            @ValidCardNumber
            @RequestParam("cardNumber") long cardNumber
    ) {
        cardService.deleteCard(mobileNumber, cardNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(), "Card deleted Successfully!"));
    }
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(version);
    }

    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfoDto> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(cardsContactInfoDto);
    }

}
