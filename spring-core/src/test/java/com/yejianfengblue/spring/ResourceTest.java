package com.yejianfengblue.spring;

import com.yejianfengblue.spring.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yejianfengblue
 */
@SpringJUnitConfig(AppConfig.class)
class ResourceTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    void givenAnnotationConfigAppContext_WhenGetResourceWithPrefixClasspath_ThenReturnClassPathResource() {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Resource classPathResource = ctx.getResource("classpath:EmptyTextFile.txt");
        assertTrue(classPathResource instanceof ClassPathResource);
        assertTrue(classPathResource.exists());
    }

    @Test
    void givenAnnotationConfigAppContext_WhenGetResourceWithoutPrefix_ThenReturnClassPathResource() {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Resource noPrefixResource = ctx.getResource("EmptyTextFile.txt");
        assertTrue(noPrefixResource instanceof ClassPathResource);
        assertTrue(noPrefixResource.exists());
    }

    @Test
    void givenAnnotationConfigAppContext_WhenGetResourceWithNonexistentPath_ThenResourceObjectIsCreated() {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        // Even though the actual resource does't exist, a Resource object is created
        Resource noPrefixResource = ctx.getResource("EmptyTextFile.txt");
        assertNotNull(noPrefixResource);
        assertTrue(noPrefixResource.exists());
    }

    @Test
    void givenAnnotationConfigAppContext_WhenGetResourceWithPrefixHttp_ThenReturnUrlResource() {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Resource httpResource = ctx.getResource("https://www.ietf.org");
        assertTrue(httpResource instanceof UrlResource);
    }

    @Test
    void givenAnnotationConfigAppContext_WhenGetResourceWithPrefixFile_ThenReturnUrlResource() {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        // Even though the actual resource does't exist, a Resource object is created
        Resource fileResource = ctx.getResource("file://a-nonexistent-file.txt");
        assertTrue(fileResource instanceof UrlResource);
        assertFalse(fileResource.exists());
    }

    @Test
    void givenSpringJUnitConfig_WhenInjectResourceLoader_ThenReturnThenContextItself() {

        assertSame(applicationContext, resourceLoader);
    }
}
