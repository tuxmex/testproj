package org.bedu.arg.testproj.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateMemberDTO {

    @NotBlank(message = "El nombre del proyecto es obligatorio")
    private String memberName;
   @Email(message = "Teclea un email válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

}