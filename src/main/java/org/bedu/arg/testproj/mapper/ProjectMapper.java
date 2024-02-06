package org.bedu.arg.testproj.mapper;



import org.bedu.arg.testproj.dto.ProjectDTO;
import org.bedu.arg.testproj.dto.CreateProjectDTO;
import org.bedu.arg.testproj.dto.ProjectWithMembersDTO;
import org.bedu.arg.testproj.dto.UpdateProjectDTO;
import org.bedu.arg.testproj.models.Project;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectMapper {

    ProjectDTO toDTO(Project model);

    List<ProjectDTO> toDTO(List<Project> model);

    @Mapping(target = "id", ignore = true)
    Project toModel(CreateProjectDTO dto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "recordAt", ignore = true)
    @Mapping(target = "members", source = "members")
    ProjectWithMembersDTO projectToProjectWithMembersDTO(Project project);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "recordAt", ignore = true)
    void update(@MappingTarget Project model, UpdateProjectDTO dto);

    //ToDo
    //MemberInProjectDTO memberToMemberInProjectDTO(Member member);

}
