package com.example.employeemanagement.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Leavee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name="Email", nullable = false)
    private String email;
    @Column(name = "Description", nullable = false)
   private String description;
    @Column(name = "FromDate", nullable = false)
    private String fromDate;
    @Column(name = "ToDate", nullable = false)
    private String toDate;
    @Column(name = "Status", nullable = false)
   private String status;

}
