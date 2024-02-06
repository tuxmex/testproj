package org.bedu.arg.testproj.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    @Schema(description = "Código de error")
    private String code;
    @Schema(description = "Mensaje del error")
    private String message;
    @Schema(description = "Detalles del error")
    private Object details;
}