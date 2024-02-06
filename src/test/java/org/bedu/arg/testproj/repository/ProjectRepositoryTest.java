package org.bedu.arg.testproj.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.bedu.arg.testproj.models.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ProjectRepositoryTest {
    @Autowired
    ProjectRepository repository;
    @Autowired
    TestEntityManager manager;


    @Test
    @DisplayName("Repository should be injected")
    void smokeTest() {
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Repository should filter projects by projectName")
    void findByProjectNameTest() {

        Project project1 = new Project();
        Project project2 = new Project();
        Project project3 = new Project();

        project1.setProjectName("Project 1");
        project1.setImage("image1.jpg");
        project1.setVideo("video1.mp4");

        project2.setProjectName("Project 2");
        project2.setImage("image2.jpg");
        project2.setVideo("video2.mp4");

        project3.setProjectName("Project 3");
        project3.setImage("image3.jpg");
        project3.setVideo("video3.mp4");

        // Crea los registros en la base de datos de prueba (h2)
        manager.persist(project1);
        manager.persist(project2);
        manager.persist(project3);

        List<Project> result = repository.findByProjectNameContaining("Project");

        assertTrue(result.size() == 3);
    }
}
