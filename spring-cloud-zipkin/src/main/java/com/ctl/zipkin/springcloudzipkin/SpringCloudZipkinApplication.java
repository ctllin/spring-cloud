package com.ctl.zipkin.springcloudzipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class SpringCloudZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudZipkinApplication.class, args);
	}
	//linux系统可以启动,window不可以启动(8080冲突或其他原因)
}
//http://127.0.0.1:8080/zipkin/
//2019-04-10 22:04:47.094  INFO 6532 --- [           main] c.l.armeria.common.util.SystemInfo       : Hostname: ctl-pc (from 'hostname' command)
//2019-04-10 22:04:47.782  INFO 6532 --- [oss-http-*:8080] com.linecorp.armeria.server.Server       : Serving HTTP at /0:0:0:0:0:0:0:0:8080 - http://127.0.0.1:8080/
//2019-04-10 22:04:47.783  INFO 6532 --- [           main] c.l.a.spring.ArmeriaAutoConfiguration    : Armeria server started at ports: {/0:0:0:0:0:0:0:0:8080=ServerPort(/0:0:0:0:0:0:0:0:8080, [http])}
//2019-04-10 22:04:47.908  INFO 6532 --- [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 2 endpoint(s) beneath base path '/actuator'
//2019-04-10 22:04:47.971  INFO 6532 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 9412 (http) with context path '/zipkinServer'
//java -jar zipkin-server-2.12.8-exec.jar --server.port=8081