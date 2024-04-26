package com.globaldatatek.onboarding.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_resume")
public class UserResume {
    @Id
    private Long id;

    @Column(name = "downloadable_url", nullable = false)
    private String downloadableUrl;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private User userInfo;
}