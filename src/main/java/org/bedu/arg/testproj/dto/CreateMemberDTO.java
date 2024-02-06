package org.bedu.arg.testproj.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateMemberDTO {

    @NotBlank(message = "El nombre del integrante es obligatorio")
    private String memberName;

    @Email(message = "Debe de ser un email valido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

}