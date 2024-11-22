package com.asac.study_hub.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer profileId;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(length=20, nullable=false)
    private String username;
    private String profileImg;

    @Column(columnDefinition="TEXT")
    private String introduce;
}
