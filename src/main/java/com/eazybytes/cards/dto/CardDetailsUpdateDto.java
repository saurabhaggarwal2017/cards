package com.eazybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
        title = "CardUpdate",
        description = "Schema to hold card update information"
)
@Data
public class CardDetailsUpdateDto {
    @Size(min = 3, max = 14, message = "card owner name should be in 3 to 14 words.")
    private String cardOwnerName;
    @Min(value = 10000, message = "Card limit should be more than 10,000 rs.")
    @Max(value = 100000, message = "Card limit should be less than 1,00,000 rs.")
    private int cardLimit;
}
