package org.bedu.arg.testproj.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.bedu.arg.testproj.dto.PatchProjectDTO;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="project_name", length = 100, nullable = false)
    private String projectName;
    @Column(length = 200, nullable = false)
    private String image;
    @Column(length = 200, nullable = false)
    private String video;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="record_at")
    private Date recordAt;
    @OneToMany(mappedBy = "project")
    private List<Member> members;

    @PrePersist
    protected void onCreate() {
        recordAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        recordAt = new Date();
    }

    public void updateFromPatchDTO(PatchProjectDTO patchDTO) {
        if (patchDTO.getProjectName() != null) {
            this.projectName = patchDTO.getProjectName();
        }
        if (patchDTO.getImage() != null) {
            this.image = patchDTO.getImage();
        }
        if (patchDTO.getVideo() != null) {
            this.video = patchDTO.getVideo();
        }
    }

}
