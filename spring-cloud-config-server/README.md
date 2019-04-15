访问服务端 http://localhost:8001/spring-cloud-config/dev (#8081为server.port)
    {"name":"spring-cloud-config","profiles":["dev"],"label":null,"version":null,"state":null,"propertySources":[{"name":"https://github.com/ctllin/spring-cloud/config-repo/spring-cloud-config-dev.properties","source":{"person.name":"ctl","env":"dev"}}]}

访问客户端 http://localhost:8002/name 可以返回在git中配置的person.name
修改git配置文件后服务器会更新为最新的配置,客户端不会更新说明获取的信息还是旧的参数，这是为什么呢？
因为spirngboot项目只有在启动的时候才会获取配置文件的值，修改github信息后，client端并没有在次去获取，所以导致这个问题

http://localhost:8002/refresh 会更新为最新的配置因为在controller增加了下面的配置
@RefreshScope // 使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中。



特别注意：上面这些与spring-cloud相关的属性必须配置在bootstrap.properties中，config部分内容才能被正确加载。
因为config的相关配置会先于application.properties，而bootstrap.properties的加载也是先于application.properties。
client中有些配置文件需要放到bootstrap.properties
在bootstrap.properties有management.context-path=/admin  management.security.enabled=false 配置
调用curl -XPOST http://192.168.6.27:8002/admin/refresh后git更新后的内容才会刷新(没有@RefreshScope依旧不会刷新)