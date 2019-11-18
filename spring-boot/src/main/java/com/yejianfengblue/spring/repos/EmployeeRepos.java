package com.yejianfengblue.spring.repos;

import com.yejianfengblue.spring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yejianfengblue
 */
@Repository
public interface EmployeeRepos extends JpaRepository<Employee, Long> {
}
