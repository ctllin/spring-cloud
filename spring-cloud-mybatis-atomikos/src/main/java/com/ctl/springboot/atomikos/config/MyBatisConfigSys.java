package com.ctl.springboot.atomikos.config;


import java.sql.SQLException;
import javax.annotation.Resource;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.context.annotation.Primary;


/**
 * <p>Title: MyBatisConfigsys</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 20sys8</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version sys.0
 * @date 20sys9-03-27 sys4:29
 */
@Configuration
// basePackages 最好分开配置 如果放在同一个文件夹可能会报错
@MapperScan(basePackages = "com.ctl.springboot.atomikos.mappersys", sqlSessionTemplateRef = "sqlSessionTemplateSys", sqlSessionFactoryRef = "sqlSessionFactorySys")
public class MyBatisConfigSys {
    // 配置数据源
    @Bean(name = "dataSourceSys")
    //@Primary
    public DataSource dataSource(DBConfigSys dbConfigSys) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(dbConfigSys.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(dbConfigSys.getPassword());
        mysqlXaDataSource.setUser(dbConfigSys.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        // 将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("dataSourceSys");

        xaDataSource.setMinPoolSize(dbConfigSys.getMinPoolSize());
        xaDataSource.setMaxPoolSize(dbConfigSys.getMaxPoolSize());
        xaDataSource.setMaxLifetime(dbConfigSys.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(dbConfigSys.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(dbConfigSys.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(dbConfigSys.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(dbConfigSys.getMaxIdleTime());
        xaDataSource.setTestQuery(dbConfigSys.getTestQuery());
        return xaDataSource;
    }

    //@Primary
    @Bean(name = "sqlSessionFactorySys")
    public SqlSessionFactory SqlSessionFactory(@Qualifier("dataSourceSys") DataSource dataSource)throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    //@Primary
    @Bean(name = "sqlSessionTemplateSys")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactorySys") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
