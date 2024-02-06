package org.bedu.arg.testproj.controllers;

import jakarta.validation.Valid;
import org.bedu.arg.testproj.dto.CreateProjectDTO;
import org.bedu.arg.testproj.dto.PatchProjectDTO;
import org.bedu.arg.testproj.dto.ProjectDTO;
import org.bedu.arg.testproj.dto.UpdateProjectDTO;
import org.bedu.arg.testproj.exceptions.ProjectNotFoundException;
import org.bedu.arg.testproj.models.Project;
import org.bedu.arg.testproj.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDTO> findAll() {
        return projectService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDTO save(@Valid @RequestBody CreateProjectDTO data) {
        return projectService.save(data);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long id, @Valid @RequestBody UpdateProjectDTO data) throws ProjectNotFoundException {
        projectService.update(id, data);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        projectService  .deleteById(id);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<Project> patchProject(
            @PathVariable long projectId,
            @RequestBody PatchProjectDTO patchDTO) throws ProjectNotFoundException {
        Project updatedProject = projectService.patchProject(projectId, patchDTO);
        return ResponseEntity.ok(updatedProject);
    }
}