package org.bedu.arg.testproj.controllers;

import org.bedu.arg.testproj.dto.CreateProjectDTO;
import org.bedu.arg.testproj.dto.PatchProjectDTO;
import org.bedu.arg.testproj.dto.ProjectDTO;
import org.bedu.arg.testproj.dto.UpdateProjectDTO;
import org.bedu.arg.testproj.exceptions.ProjectNotFoundException;
import org.bedu.arg.testproj.models.Project;
import org.bedu.arg.testproj.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_shouldReturnListOfProjects() {
        // Arrange
        List<ProjectDTO> expectedProjects = Collections.singletonList(new ProjectDTO(/* Project details */));
        when(projectService.findAll()).thenReturn(expectedProjects);

        // Act
        List<ProjectDTO> actualProjects = projectController.findAll();

        // Assert
        assertEquals(expectedProjects, actualProjects);
    }

    @Test
    void save_shouldReturnSavedProject() {
        // Arrange
        CreateProjectDTO createProjectDTO = new CreateProjectDTO(/* Project details for creation */);
        ProjectDTO expectedSavedProject = new ProjectDTO(/* Saved project details */);
        when(projectService.save(createProjectDTO)).thenReturn(expectedSavedProject);

        // Act
        ProjectDTO actualSavedProject = projectController.save(createProjectDTO);

        // Assert
        assertEquals(expectedSavedProject, actualSavedProject);
    }

    @Test
    void update_shouldNotThrowException() throws ProjectNotFoundException {
        // Arrange
        long projectId = 1L;
        UpdateProjectDTO updateProjectDTO = new UpdateProjectDTO(/* Updated project details */);

        // Act
        projectController.update(projectId, updateProjectDTO);

        // Assert
        verify(projectService, times(1)).update(projectId, updateProjectDTO);
    }

 /*   
    @Test
    void update_shouldThrowNotFoundException(){
        // Arrange
        long nonExistingProjectId = 199L;
        UpdateProjectDTO updateProjectDTO = new UpdateProjectDTO();
        updateProjectDTO.setVideo("abc.mpg");
        updateProjectDTO.setImage("data.jpg");
        updateProjectDTO.setProjectName("Data");
        // Act and Assert
         try {
            projectController.update(nonExistingProjectId, updateProjectDTO);
            fail("Se esperaba que se lanzara ProjectNotFoundException");
        } catch (ProjectNotFoundException e) {
           e.printStackTrace();
        }        
    }
*/
    @Test
    void delete_shouldNotThrowException() {
        // Arrange
        long projectId = 1L;

        // Act
        projectController.delete(projectId);

        // Assert
        verify(projectService, times(1)).deleteById(projectId);
    }

    @Test
    void patchProject_shouldReturnUpdatedProject() throws ProjectNotFoundException {
        // Arrange
        long projectId = 1L;
        PatchProjectDTO patchProjectDTO = new PatchProjectDTO(/* Patched project details */);
        Project expectedUpdatedProject = new Project(/* Updated project details */);
        when(projectService.patchProject(projectId, patchProjectDTO)).thenReturn(expectedUpdatedProject);

        // Act
        ResponseEntity<Project> responseEntity = projectController.patchProject(projectId, patchProjectDTO);
        Project actualUpdatedProject = responseEntity.getBody();

        // Assert
        assertEquals(expectedUpdatedProject, actualUpdatedProject);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
