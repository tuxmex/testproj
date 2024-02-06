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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@DataJpaTest
//@SpringBootTest
//@Transactional

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ProjectServiceIntegrationTest {

    @Autowired
    ProjectService projectService;
    @MockBean
    ProjectRepository projectRepository;
    @Autowired
    ProjectMapper projectMapper;

    @Test
    void saveAndFindAll() {
        // Arrange
        CreateProjectDTO createProjectDTO = new CreateProjectDTO();
        createProjectDTO.setImage("image2.jpg");
        createProjectDTO.setVideo("video.mp4");
        createProjectDTO.setProjectName("Mi proyecto X");

        // Act
        ProjectDTO savedProject = projectService.save(createProjectDTO);
        System.out.println("Projecto ARG: "+savedProject);
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
