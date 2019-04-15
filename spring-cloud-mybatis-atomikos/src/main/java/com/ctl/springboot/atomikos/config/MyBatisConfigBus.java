package com.ctl.springboot.atomikos.config;


import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * <p>Title: MyBatisConfig2</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2028</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 2.0
 * @date 2029-03-27 24:29
 */
@Configuration
@MapperScan(basePackages = "com.ctl.springboot.atomikos.mapperbus", sqlSessionTemplateRef = "sqlSessionTemplateBus", sqlSessionFactoryRef = "sqlSessionFactoryBus")
public class MyBatisConfigBus {

    // 配置数据源
    @Bean(name = "dataSourceBus")
    public DataSource dataSource(DBConfigBus dbConfigBus) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(dbConfigBus.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(dbConfigBus.getPassword());
        mysqlXaDataSource.setUser(dbConfigBus.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        // 将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("dataSourceBus");

        xaDataSource.setMinPoolSize(dbConfigBus.getMinPoolSize());
        xaDataSource.setMaxPoolSize(dbConfigBus.getMaxPoolSize());
        xaDataSource.setMaxLifetime(dbConfigBus.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(dbConfigBus.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(dbConfigBus.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(dbConfigBus.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(dbConfigBus.getMaxIdleTime());
        xaDataSource.setTestQuery(dbConfigBus.getTestQuery());
        return xaDataSource;
    }

    @Bean(name = "sqlSessionFactoryBus")
    public SqlSessionFactory SqlSessionFactory(@Qualifier("dataSourceBus") DataSource dataSource)throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplateBus")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactoryBus") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
