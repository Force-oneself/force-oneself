package com.quan.common.util.code.generate;

import com.quan.application.db.mysql.MySQLUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

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
