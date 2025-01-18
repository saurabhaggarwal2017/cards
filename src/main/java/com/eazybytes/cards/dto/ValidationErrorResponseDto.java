package com.eazybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@Schema(
        name = "Validation error response",
        description = "Schema to hold validation error information"
)
public class ValidationErrorResponseDto {
    @Schema(
            description = "API path invoked by client"
    )
    private String apiPath;
    @Schema(
            description = "Total number of errors occur."
    )
    int numberOfError;
    @Schema(
            description = "Error code representing the error happened"
    )
    private String errorCode;
    @Schema(
            description = "Error message representing the error happened"
    )
    private String errorMessage;
    @Schema(
            description = "Contains All Errors."
    )
    private Map<String, String> errors;
    @Schema(
            description = "Time representing when the error happened"
    )
    private LocalDateTime errorTime;
}


