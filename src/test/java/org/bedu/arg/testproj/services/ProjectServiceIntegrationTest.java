package org.bedu.arg.testproj.services;

import org.bedu.arg.testproj.dto.CreateProjectDTO;
import org.bedu.arg.testproj.dto.PatchProjectDTO;
import org.bedu.arg.testproj.dto.ProjectDTO;
import org.bedu.arg.testproj.dto.UpdateProjectDTO;
import org.bedu.arg.testproj.exceptions.ProjectNotFoundException;
import org.bedu.arg.testproj.mapper.ProjectMapper;
import org.bedu.arg.testproj.models.Project;
import org.bedu.arg.testproj.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@SpringBootTest
@Transactional
class ProjectServiceIntegrationTest {

    ProjectService projectService;
    ProjectRepository projectRepository;
    ProjectMapper projectMapper;

    
    @Autowired
    public ProjectServiceIntegrationTest(ProjectService projectService, ProjectRepository projectRepository,
            ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Test
    void saveAndFindAll() {
        // Arrange
        CreateProjectDTO createProjectDTO = new CreateProjectDTO(/* Datos del proyecto a crear */);

        // Act
        ProjectDTO savedProject = projectService.save(createProjectDTO);
        ProjectDTO foundProject = projectService.findAll().get(0);

        // Assert
        assertEquals(savedProject.getProjectName(), foundProject.getProjectName());
        // Agrega más aserciones según sea necesario
    }

    @Test
    void updateAndFindById() throws ProjectNotFoundException {
        // Arrange
        CreateProjectDTO createProjectDTO = new CreateProjectDTO(/* Datos del proyecto a crear */);
        ProjectDTO savedProject = projectService.save(createProjectDTO);

        UpdateProjectDTO updateProjectDTO = new UpdateProjectDTO(/* Datos actualizados del proyecto */);

        // Act
        projectService.update(savedProject.getId(), updateProjectDTO);
        ProjectDTO updatedProject = projectService.findAll().get(0);

        // Assert
        assertEquals(updateProjectDTO.getImage(), updatedProject.getImage());
        assertEquals(updateProjectDTO.getProjectName(), updatedProject.getProjectName());
        assertEquals(updateProjectDTO.getVideo(), updatedProject.getVideo());
    }

    @Test
    void updateNonExistingProject() {
        // Act and Assert
        assertThrows(ProjectNotFoundException.class, () -> projectService.update(999L, new UpdateProjectDTO()));
    }

    @Test
    void patchProjectAndFindById() throws ProjectNotFoundException {
        // Arrange
        CreateProjectDTO createProjectDTO = new CreateProjectDTO(/* Datos del proyecto a crear */);
        ProjectDTO savedProject = projectService.save(createProjectDTO);

        PatchProjectDTO patchProjectDTO = new PatchProjectDTO(/* Datos parchados del proyecto */);

        // Act
        Project patchedProject = projectService.patchProject(savedProject.getId(), patchProjectDTO);
        ProjectDTO foundPatchedProject = projectService.findAll().get(0);

        // Assert
        assertEquals(patchProjectDTO.getProjectName(), patchedProject.getProjectName());
        assertEquals(patchProjectDTO.getImage(), foundPatchedProject.getImage());
        assertEquals(patchProjectDTO.getVideo(), foundPatchedProject.getVideo());
    }

}
