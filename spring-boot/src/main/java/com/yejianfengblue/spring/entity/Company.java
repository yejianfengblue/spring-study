package com.yejianfengblue.spring.entity;

import lombok.Data;

import java.util.List;
import java.util.UUID;
import javax.persistence.*;

/**
 * @author yejianfengblue
 */
@Entity
@Data
public class Company {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Employee> employees;
}
