package com.yejianfengblue.spring.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
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

    private String name;

    private BigDecimal salary;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    @ToString.Exclude
    private Company company;
}
