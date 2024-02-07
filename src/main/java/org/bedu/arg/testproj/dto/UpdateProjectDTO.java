package org.bedu.arg.testproj.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class UpdateProjectDTO {

    @NotBlank(message = "El nombre del proyecto es obligatorio")
    private String projectName;
    @NotBlank(message = "El video es obligatorio")
    private String video;
    @NotBlank(message = "La imagen es obligatoria")
    private String image;

}