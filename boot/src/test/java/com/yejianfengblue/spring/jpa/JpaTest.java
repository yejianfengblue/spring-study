package com.yejianfengblue.spring.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author yejianfengblue
 */
@SpringBootTest
@Transactional
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
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @Test
    void insertMultipleEntities() {

        for (long i = 0; i < 22; i++) {

            MyEntity myEntity = new MyEntity();
            myEntity.setMyColumn("dummy " + i);
            entityManager.persist(myEntity);
        }
        entityManager.flush();
    }
}
