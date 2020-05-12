package com.ctl.springclouddubbohystrix.controller;

import com.alibaba.fastjson.JSON;
import com.ctl.springclouddubbohystrix.mapper.ResourceMapper;
import com.ctl.springclouddubbohystrix.mapper.RoleResourceMapper;
import com.ctl.springclouddubbohystrix.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
@RestController
public class AclController {


    @Autowired(required = false)
    private ResourceMapper resourceMapper;
    @Autowired(required = false)
    private RoleResourceMapper roleResourceMapper;
    @Autowired(required = false)
    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired(required = false)
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping(value = "resource")
    public String resource(String id) {
        String[] beanDefinitionNames = SpringContextUtil.getApplicationContext().getBeanDefinitionNames();
        for (int i = 0; i < beanDefinitionNames.length; i++) {
            if (beanDefinitionNames[i].startsWith("org"))
                continue;
            //System.out.println(beanDefinitionNames[i]);
        }

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        Connection connection = sqlSessionFactory.openSession().getConnection();
        try {
            String sql1 = "delete from resource where remarks!='mm1'";
            String sql2 = "delete from role_resource where remarks!='mm1'";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql1);
            int executeUpdate = preparedStatement2.executeUpdate();
            log.info("delete from resource result={}", executeUpdate);
            preparedStatement2 = connection.prepareStatement(sql2);
            executeUpdate = preparedStatement2.executeUpdate();
            log.info("delete from role_resource result={}\n", executeUpdate);

        } catch (SQLException e) {
            log.error("", e);
        }
        sqlSession.commit();
        //sqlSession.close();
        String merchantIds[] = "1250001,1840002,2156001,4528001,1528001,1642001".split(",");
        for (int i = 0; i < merchantIds.length; i++) {
            String str = resStr.replaceAll("4156003", merchantIds[i]) + ";";
            int executeUpdate;
            try {
                PreparedStatement preparedStatement2 = connection.prepareStatement(str);
                executeUpdate = preparedStatement2.executeUpdate();
                log.info("商户={},resource result={}", merchantIds[i], executeUpdate);
                String str2 = rrStr.replaceAll("4156003", merchantIds[i]) + ";";
                preparedStatement2 = connection.prepareStatement(str2);
                executeUpdate = preparedStatement2.executeUpdate();
                log.info("商户={},role_resource result={}\n", merchantIds[i], executeUpdate);
            } catch (SQLException e) {
            }
            //System.out.println(str + "\n");
            //System.out.println(str2 + "\n");
        }
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("", e);
        }
        return JSON.toJSONString(resourceMapper.selectById(id));
    }

    static String resStr = "INSERT INTO resource (\n" +
            "id,\n" +
            "merchant_id,\n" +
            "app_id,\n" +
            "NAME,\n" +
            "name_internationalization,\n" +
            "url,\n" +
            "skip_path,\n" +
            "description,\n" +
            "icon,\n" +
            "icon_type,\n" +
            "pid,\n" +
            "seq,\n" +
            "state,\n" +
            "resource_type,\n" +
            "default_data,\n" +
            "remarks,\n" +
            "gmt_create,\n" +
            "gmt_modified,\n" +
            "del_flag \n" +
            ") SELECT\n" +
            "concat( '4156003', SUBSTR( resource.id, 2, 7 ) ) AS id,\n" +
            "'4156003' AS merchant_id,\n" +
            "resource.app_id,\n" +
            "resource.`name`,\n" +
            "resource.name_internationalization,\n" +
            "resource.url,\n" +
            "resource.skip_path,\n" +
            "resource.description,\n" +
            "resource.icon,\n" +
            "resource.icon_type,\n" +
            "concat( '4156003', SUBSTR( resource.pid, 2, 7 ) ) AS pid,\n" +
            "resource.seq,\n" +
            "resource.state,\n" +
            "resource.resource_type,\n" +
            "resource.default_data,\n" +
            "'4156003' AS remarks,\n" +
            "resource.gmt_create,\n" +
            "resource.gmt_modified,\n" +
            "resource.del_flag \n" +
            "FROM\n" +
            "\tresource \n" +
            "WHERE\n" +
            "\tresource.remarks = 'mm1'";
    static String rrStr = "INSERT INTO role_resource (id, merchant_id, app_id, role_id, resource_id, remarks, gmt_create, gmt_modified, del_flag) \n" +
            "SELECT\n" +
            "\tUUID_SHORT( ) AS id,\n" +
            "\tsubstr( '415600300001', 1, LENGTH( '415600300001' ) - 5 ) AS merchant_id,\n" +
            "\trole_resource.app_id,\n" +
            "\tconcat( substr( '415600300001', 1, LENGTH( '415600300001' ) - 5 ), SUBSTR( role_resource.role_id, 2, 7 ) ) AS role_id,\n" +
            "\tconcat( substr( '415600300001', 1, LENGTH( '415600300001' ) - 5 ), SUBSTR( role_resource.resource_id, 2, 7 ) ) AS resource_id,\n" +
            "\tsubstr( '415600300001', 1, LENGTH( '415600300001' ) - 5 ) as remarks,\n" +
            "\trole_resource.gmt_create,\n" +
            "\trole_resource.gmt_modified,\n" +
            "\trole_resource.del_flag \n" +
            "FROM\n" +
            "\trole_resource \n" +
            "WHERE\n" +
            "\tremarks = 'mm1'";

}
//http://localhost:9090/hi
//http://localhost:9090/hi2
//http://localhost:9090/hystrix
//http://localhost:9090/hystrix.stream