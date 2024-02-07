package org.bedu.arg.testproj.models;

import org.bedu.arg.testproj.dto.PatchMemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "member_name")
    private String memberName;

    @Column(length = 180)
    private String email;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Override
    public String toString() {
        return "Member [id=" + id + ", memberName=" + memberName + ", email=" + email + ", project=" + project + "]";
    }

    public void updateFromPatchDTO(PatchMemberDTO patchDTO) {
        if (patchDTO.getMemberName() != null) {
            this.memberName = patchDTO.getMemberName();
        }
        if (patchDTO.getEmail() != null) {
            this.email = patchDTO.getMemberName();
        }    
    }

    
}
