package com.yejianfengblue.spring.jpa;

import com.yejianfengblue.spring.entity.Employee;
import com.yejianfengblue.spring.repos.CompanyRepos;
import com.yejianfengblue.spring.repos.EmployeeRepos;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author yejianfengblue
 */
@DataJpaTest
@Transactional
class JpaTest {

    @Autowired
    private CompanyRepos companyRepos;

    @Autowired
    private EmployeeRepos employeeRepos;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Test
    void givenSavedEmployeeEntity_whenFindFromRepos_thenSameObject() {

        Employee apple = new Employee();
        apple.setName("Apple");
        apple.setSalary(new BigDecimal(100));
        Employee savedApple = employeeRepos.save(apple);
        assertSame(apple, savedApple);
        employeeRepos.flush();
        Optional<Employee> foundApple = employeeRepos.findById(savedApple.getId());
        assertTrue(foundApple.isPresent());
        assertSame(apple, foundApple);
    }
}
