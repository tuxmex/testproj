package org.bedu.arg.testproj.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PatchProjectDTO {
    @NotBlank(message = "El nombre del proyecto es obligatorio")
    private String projectName;
    @NotBlank(message = "La imagen es obligatoria")
    private String image;
    @NotBlank(message = "El url del video es obligatoria")
    private String video;

}
