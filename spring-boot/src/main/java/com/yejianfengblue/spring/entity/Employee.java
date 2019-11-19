package com.yejianfengblue.spring.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author yejianfengblue
 */
@Entity
@Data
public class Employee {

    @Id
    @SequenceGenerator(name = "EMPLOYEE_ID_SEQ_GEN", sequenceName = "EMPLOYEE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_ID_SEQ_GEN")
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer salary;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Company company;
}
