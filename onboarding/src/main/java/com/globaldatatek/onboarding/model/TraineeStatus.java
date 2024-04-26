package com.globaldatatek.onboarding.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "trainee_status")
public class TraineeStatus {
    @Id
    private Long id;

    @Column(name = "batch_no", nullable = false)
    private String batchNo;

    @Column(name = "interviewed_by", nullable = false)
    private String interviewedBy;

    @Column(name = "interviewed_date", nullable = false)
    private Date interviewedDate;

    @Column(nullable = false)
    private String feedback;

    @Column(nullable = false)
    private String status;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private User userInfo;
}