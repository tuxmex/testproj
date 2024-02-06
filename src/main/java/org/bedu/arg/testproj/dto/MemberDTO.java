package org.bedu.arg.testproj.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class MemberDTO {
    @Schema(description = "Identificador del integrante del proyecto", example = "33")
    private Long id;
    @Schema(description = "Nombre del integrante del proyecto", example = "Maria Sanchez")
    private String memberName;
    @Schema(description = "Correo electrónico del integrante del equipo", example = "mariaschez33@gmail.com")
    private String email;

}
