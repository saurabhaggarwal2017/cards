package com.eazybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema
public class ErrorResponseDto {
    @Schema(
            description = "API path invoked by client"
    )
    private String apiPath;
    @Schema(
            description = "Error code representing the error happened"
    )
    private String statusCode;
    @Schema(
            description = "Error message representing the error happened"
    )
    private String errorMessage;
    @Schema(
            description = "Time representing when the error happened"
    )
    private LocalDateTime errorTime;
}
