package org.bedu.arg.testproj.models;

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
}
