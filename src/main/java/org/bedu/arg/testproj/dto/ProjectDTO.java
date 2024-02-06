package org.bedu.arg.testproj.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProjectDTO {

    @Schema(description = "Identificador del proyecto", example = "45")
    private long id;
    @Schema(description = "Nombre del proyecto", example = "Angel de cabecera")
    private String projectName;
    @Schema(description = "Enlace del video", example = "https://youtu.be/sch-Cy9bZkE")
    private String video;
    @Schema(description = "Enlace de la imagen", example = "/img/image.jpg")
    private String image;

}
