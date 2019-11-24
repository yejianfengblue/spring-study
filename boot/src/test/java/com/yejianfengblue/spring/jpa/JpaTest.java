package com.yejianfengblue.spring.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author yejianfengblue
 */
@DataJpaTest(showSql = false)  // show sql and param is enabled in app.prop by setting hibernate logging level
class JpaTest {

    @Autowired
    private EntityManager entityManager;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Entity
    @Getter
    @Setter
    @ToString
    private static class MyEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String myColumn;
    }

    @Test
    void simpleJpaTest() {

        MyEntity myEntity = new MyEntity();
        myEntity.setMyColumn("dummy");

        log.info("myEntity before persist = {}", myEntity);
        entityManager.persist(myEntity);
        log.info("myEntity after persist = {}", myEntity);
        assertNotNull(myEntity.getId());
        entityManager.flush();
        entityManager.clear();

        MyEntity foundMyEntity = entityManager.find(MyEntity.class, myEntity.getId());
        log.info("foundMyEntity find by Id {} = {}", myEntity.getId(), foundMyEntity);
        assertNotNull(foundMyEntity);
    }
}
