package org.bedu.arg.testproj.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateProjectDTO {

    @NotBlank(message = "El nombre del proyecto es obligatorio")
    private String projectName;

    @NotBlank(message = "El url del video es obligatoria")
    private String video;

    @NotBlank(message = "La imagen es obligatoria")
    private String image;

}