package org.bedu.arg.testproj.services;

import jakarta.transaction.Transactional;
import org.bedu.arg.testproj.dto.CreateProjectDTO;
import org.bedu.arg.testproj.dto.PatchProjectDTO;
import org.bedu.arg.testproj.dto.ProjectDTO;
import org.bedu.arg.testproj.dto.UpdateProjectDTO;
import org.bedu.arg.testproj.exceptions.ProjectNotFoundException;
import org.bedu.arg.testproj.mapper.ProjectMapper;
import org.bedu.arg.testproj.models.Project;
import org.bedu.arg.testproj.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository repository;

    private final ProjectMapper mapper;

    @Autowired(required = false)
    public ProjectService(ProjectRepository repository, ProjectMapper mapper) {
        this.mapper = mapper;
        this.repository = repository;
    }


    public List<ProjectDTO> findAll() {
        List<Project> data = repository.findAll();
        return mapper.toDTO(data);
    }

    @Transactional
    public ProjectDTO save(CreateProjectDTO data) {
        Project result = repository.save(mapper.toModel(data));
        return mapper.toDTO(result);
    }

    public void update(long id, UpdateProjectDTO data) throws ProjectNotFoundException{
        Project project = repository.findById(id)
            .orElseThrow(() -> new ProjectNotFoundException(id));
        mapper.update(project, data);
        repository.save(project);
    }

    public Project patchProject(long projectId, PatchProjectDTO patchDTO) throws ProjectNotFoundException {
        Project project = repository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));
       project.updateFromPatchDTO(patchDTO);
       return repository.save(project);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
