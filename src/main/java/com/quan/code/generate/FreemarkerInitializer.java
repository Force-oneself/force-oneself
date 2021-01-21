package com.quan.code.generate;

import com.quan.db.mysql.MySQLUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description: Freemarker初始化器
 * @Author heyq
 * @Date 2021-01-01
 **/
public class FreemarkerInitializer implements Initializer<FreemarkerGenerator> {

    @Override
    public void init(FreemarkerGenerator generator) {
        // 初始化Freemarker生成器
        Connection conn = MySQLUtils.getConnection();
        try {
            DatabaseMetaData metaData = conn.getMetaData();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
