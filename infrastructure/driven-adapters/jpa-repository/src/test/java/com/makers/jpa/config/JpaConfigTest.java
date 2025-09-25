package com.makers.jpa.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JpaConfigTest {


    @Mock
    DataSource dataSource;

    private DBParams dbParams;
    private JpaConfig jpaConfigUnderTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dbParams = new DBParams();
        dbParams.setUrl("jdbc:h2:mem:test");
        dbParams.setUsername("postgres");
        dbParams.setPassword("1234");

        jpaConfigUnderTest = new JpaConfig(dbParams);
    }

    @Test
    void dbSecretTest() {
        assertEquals("postgres", dbParams.getUsername());
        assertEquals("123", dbParams.getPassword());
    }

    @Test
    void datasourceTest() {
        final DataSource result = jpaConfigUnderTest.datasource(dbParams, "org.h2.Driver");

        assertNotNull(result);
    }

    @Test
    void entityManagerFactoryTest() {

        final LocalContainerEntityManagerFactoryBean result =
                jpaConfigUnderTest.entityManagerFactory(dataSource, "dialect");

        assertNotNull(result);
    }
}
