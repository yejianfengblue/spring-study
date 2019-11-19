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
    void givenNewCreated1CompanyWith2Employee_whenDetachAndFindAgain_thenEqualsButNotSameObject() {

        // given
        Company magicianGirls = new Company();
        magicianGirls.setName("Magician Girls");

        Employee apple = new Employee();
        apple.setName("Apple Magician Girl");
        apple.setSalary(1000);
        apple.setCompany(magicianGirls);
        Employee berry = new Employee();
        berry.setName("Berry Magician Girl");
        berry.setSalary(2000);
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
        // after detach and find again, not the same object
        assertNotSame(magicianGirls, foundMagicianGirlsAfterDetach.get());
        // the collection member is deep equals
        assertIterableEquals(magicianGirls.getEmployees(), foundMagicianGirlsAfterDetach.get().getEmployees());
        // the collection member is not equals, because the collection classes like PersistentBag or PersistentList
        // from Hibernate don't override equals() and hashCode()
        assertNotEquals(magicianGirls.getEmployees(), foundMagicianGirlsAfterDetach.get().getEmployees());
        // so impossible to assertEquals against the Company objects
    }
}
