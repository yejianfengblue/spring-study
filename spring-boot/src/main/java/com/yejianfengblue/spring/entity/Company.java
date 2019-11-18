package com.yejianfengblue.spring.entity;

import lombok.Data;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author yejianfengblue
 */
@Entity
@Data
public class Company {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
}
