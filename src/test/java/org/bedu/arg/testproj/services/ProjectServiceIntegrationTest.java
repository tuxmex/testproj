package org.bedu.arg.testproj.services;

import org.bedu.arg.testproj.dto.CreateProjectDTO;
import org.bedu.arg.testproj.dto.ProjectDTO;
import org.bedu.arg.testproj.dto.UpdateProjectDTO;
import org.bedu.arg.testproj.exceptions.ProjectNotFoundException;
import org.bedu.arg.testproj.mapper.ProjectMapper;
import org.bedu.arg.testproj.models.Project;
import org.bedu.arg.testproj.repository.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

//@DataJpaTest
//@SpringBootTest
//@Transactional
//@AutoConfigureTestDatabase(replace = Replace.NONE)

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProjectServiceIntegrationTest {

    @Autowired
    ProjectService projectService;
    @MockBean
    ProjectRepository projectRepository;
    @Autowired
    ProjectMapper projectMapper;
   
    @Test
  @DisplayName("Service should save a project in repository")
  void saveTest() {
    CreateProjectDTO dto = new CreateProjectDTO();
    dto.setProjectName("DomotiK UTNG");
    dto.setImage("DomotiK.jpg");
    dto.setVideo("data.mp4");
    Project model = new Project();
    model.setId(8794);
    model.setProjectName(dto.getProjectName());
    model.setImage(dto.getImage());
    model.setVideo(dto.getVideo());

    when(projectRepository.save(any(Project.class))).thenReturn(model);

    ProjectDTO result = projectService.save(dto);

    assertNotNull(result);
    assertEquals(model.getId(), result.getId());
    assertEquals(model.getProjectName(), result.getProjectName());
    assertEquals(model.getImage(), result.getImage());
    assertEquals(model.getVideo(), result.getVideo());
  }

  @Test
  @DisplayName("Service should return projects from repository")
  void findAllTest() {
    List<Project> data = new LinkedList<>();

    Project project = new Project();

    project.setId(6548);
    project.setProjectName("The project");
    project.setImage("Data");;
    project.setVideo("Data");;

    data.add(project);

    when(projectRepository.findAll()).thenReturn(data);

    List<ProjectDTO> result = projectService.findAll();
    System.out.println("Lista de Proyectos: "+ result);
    assertNotNull(result);
    assertTrue(result.size() > 0);
    assertEquals(project.getId(), result.get(0).getId());
    assertEquals(project.getProjectName(), result.get(0).getProjectName());
    assertEquals(project.getImage(), result.get(0).getImage());
  }

    @Test
    void saveAndFindAll() {
        // Arrange
        CreateProjectDTO createProjectDTO = new CreateProjectDTO();
        createProjectDTO.setImage("image2.jpg");
        createProjectDTO.setVideo("video.mp4");
        createProjectDTO.setProjectName("Mi proyecto X");

        Project savedProject = new Project();
        savedProject.setId(123);
        savedProject.setImage(createProjectDTO.getImage());
        savedProject.setVideo(createProjectDTO.getVideo());
        savedProject.setProjectName(createProjectDTO.getProjectName());

        List<Project> data = new LinkedList<>();
        data.add(savedProject);

        // Act
        when(projectRepository.save(any(Project.class))).thenReturn(savedProject);
        ProjectDTO result = projectService.save(createProjectDTO);
        System.out.println(result);
        when(projectRepository.findAll()).thenReturn(data);
        ProjectDTO foundProject = projectService.findAll().get(0);
        // Assert
        assertEquals(createProjectDTO.getProjectName(), foundProject.getProjectName());
        assertEquals(result.getProjectName(), foundProject.getProjectName());
    }


     @Test
  @DisplayName("Service should update a Project in repository")
  void updateTest() throws ProjectNotFoundException {
    UpdateProjectDTO dto = new UpdateProjectDTO();

    dto.setProjectName("Smart Office 2023");
    dto.setImage("smart_office_test.jpg");
    dto.setVideo("video_demo_so.mp4");
    Project project = new Project();

    project.setId(2874);
    project.setProjectName("Smart Office 2024");
    project.setImage("smart_office_test.jpg");
    project.setVideo("video_demo_so.mp4");

    when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));

    projectService.update(2874, dto);

    assertEquals(dto.getProjectName(), project.getProjectName());
    assertEquals(dto.getImage(), project.getImage());
    verify(projectRepository, times(1)).save(project);
  }

  @Test
  @DisplayName("Service should delete a project by id in repository")
  void deleteByIdTest() {
    projectService.deleteById(38798l);

    verify(projectRepository, times(1)).deleteById(38798l);
  }

    @Test
    void updateAndFindById() throws ProjectNotFoundException {
        // Arrange
        CreateProjectDTO createProjectDTO = new CreateProjectDTO(/* Datos del proyecto a crear */);
        ProjectDTO savedProjectDTO = projectService.save(createProjectDTO);

        UpdateProjectDTO updateProjectDTO = new UpdateProjectDTO(/* Datos actualizados del proyecto */);

        Project savedProject = new Project();
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(savedProject));
        //when(projectMapper.update(any(Project.class), eq(updateProjectDTO))).thenReturn(savedProject);

        // Act
        ProjectDTO updatedProject = projectService.findAll().get(0);

        // Assert
        assertEquals(updateProjectDTO.getImage(), updatedProject.getImage());
        assertEquals(updateProjectDTO.getProjectName(), updatedProject.getProjectName());
        assertEquals(updateProjectDTO.getVideo(), updatedProject.getVideo());
    }

    // Otras pruebas con Mockito
}


