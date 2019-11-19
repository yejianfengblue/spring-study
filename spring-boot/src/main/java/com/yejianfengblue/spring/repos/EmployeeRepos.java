package com.yejianfengblue.spring.repos;

import com.yejianfengblue.spring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author yejianfengblue
 */
@Repository
public interface EmployeeRepos extends JpaRepository<Employee, Long> {

    Optional<Employee> findByName(String name);
}
