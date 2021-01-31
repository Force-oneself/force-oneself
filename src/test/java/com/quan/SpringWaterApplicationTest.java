package com.quan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-01-31
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringWaterApplicationTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void save() throws SQLException {
        dataSource.getConnection().prepareStatement("insert into demo_cas(username, password) values ('demo', '这是一个密码')").executeUpdate();
    }
}
