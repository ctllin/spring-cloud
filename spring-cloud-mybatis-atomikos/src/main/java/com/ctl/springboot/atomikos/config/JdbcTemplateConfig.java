package com.ctl.springboot.atomikos.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;

/**
 * <p>Title: JdbcTemplateConfig</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-03-27 16:19
 */
@Component
public class JdbcTemplateConfig {
    @Bean("sysJdbcTemplate")
    public JdbcTemplate sysJdbcTemplate(@Qualifier("dataSourceSys") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean("busJdbcTemplate")
    public JdbcTemplate busJdbcTemplate(@Qualifier("dataSourceBus") DataSource ds) {
        return new JdbcTemplate(ds);
    }

}
