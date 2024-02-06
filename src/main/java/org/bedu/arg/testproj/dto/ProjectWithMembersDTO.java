package org.bedu.arg.testproj.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.bedu.arg.testproj.models.Member;

import java.util.Date;
import java.util.List;

@Data
public class ProjectWithMembersDTO {
    @Schema(description = "Identificador del proyecto", example = "40")

    private Long id;
    @Schema(description = "Nombre del proyecto", example = "Angel de cabecera")
    private String projectName;
    @Schema(description = "Enlace del video", example = "https://youtu.be/sch-Cy9bZkE")
    private String video;
    @Schema(description = "Enlace de la imagen", example = "/img/image.jpg")
    private String image;
    @Schema(description = "Fecha de registros", example = "11-11-2023")
    private Date recordAt;
    @Schema(description = "Lista de miembros del equipo", example = "{Member1: Juan Perez, Miembro2: Maria Sachez}")
    private List<Member> members;

}