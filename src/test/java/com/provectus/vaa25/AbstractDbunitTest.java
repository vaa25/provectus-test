package com.provectus.vaa25;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
public abstract class AbstractDbunitTest {

    @Autowired
    private JdbcTemplate template;

    @Before
    public void setUp() {
        this.template.update("ALTER TABLE author ALTER COLUMN id RESTART WITH 1");
        this.template.update("ALTER TABLE genre ALTER COLUMN id RESTART WITH 1");
        this.template.update("ALTER TABLE book ALTER COLUMN id RESTART WITH 1");
        this.template.update("ALTER TABLE purchase ALTER COLUMN id RESTART WITH 1");
    }

}