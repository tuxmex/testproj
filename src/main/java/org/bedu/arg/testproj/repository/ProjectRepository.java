package org.bedu.arg.testproj.repository;

import java.util.List;

import org.bedu.arg.testproj.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByImageExtension(String extension);

    List<Project> findByProjectNameContaining(String name);

}