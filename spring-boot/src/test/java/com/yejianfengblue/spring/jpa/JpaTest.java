package com.yejianfengblue.spring.jpa;

import com.yejianfengblue.spring.entity.Company;
import com.yejianfengblue.spring.entity.Employee;
import com.yejianfengblue.spring.repos.CompanyRepos;
import com.yejianfengblue.spring.repos.EmployeeRepos;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

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

    @Autowired
    private EntityManager entityManager;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Test
    void givenNewCreated1CompanyWith2Employee_whenDetachAndFindAgain_thenNotSameObject() {

        // given
        Company magicianGirls = new Company();
        magicianGirls.setName("Magician Girls");

        Employee apple = new Employee();
        apple.setName("Apple Magician Girl");
        apple.setSalary(new BigDecimal(1000));
        apple.setCompany(magicianGirls);
        Employee berry = new Employee();
        berry.setName("Berry Magician Girl");
        berry.setSalary(new BigDecimal(2000));
        berry.setCompany(magicianGirls);
        magicianGirls.setEmployees(List.of(apple, berry));

        companyRepos.saveAndFlush(magicianGirls);
        Optional<Company> foundMagicianGirls = companyRepos.findByName("Magician Girls");
        assertTrue(foundMagicianGirls.isPresent());
        assertSame(magicianGirls, foundMagicianGirls.get());

        // when
        entityManager.detach(magicianGirls);

        // then
        Optional<Company> foundMagicianGirlsAfterDetach = companyRepos.findByName("Magician Girls");
        assertTrue(foundMagicianGirlsAfterDetach.isPresent());
        assertNotSame(magicianGirls, foundMagicianGirlsAfterDetach.get());
    }
}
