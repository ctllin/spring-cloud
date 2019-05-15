git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/ctllin/spring-cloud.git
git push -u origin master

git remote add origin https://github.com/ctllin/spring-cloud.git
git push -u origin master

fatal: refusing to merge unrelated histories  解决 git pull origin master --allow-unrelated-histories

hint: 'git pull ...') before pushing again.
从提示可以看出，是由于两者不同步，因此需要先pull，进行合并然后在进行push，
git pull --rebase origin master
git push -u origin master



git add .
git commit -m 'modify'
git push -u origin master

git config --global user.name "usrname"
git config --global user.password "*password*"

spring-cloud-registry-one(127.0.0.1:1001) spring-cloud-registry-two(127.0.0.1:1002) spring-cloud-registry-three(127.0.0.1:1003) 为三个注册中心相互注册形成集群
pic为注册中心注册后的截图


mvn clean package -Dmaven.test.skip=true


Git push忽略规则及.gitignore规则不生效的解决办法
git rm -r --cached .
git add .
git commit -m "update .gitignore"
 
git pull 拉取远程分支代码

获取依赖数
mvn dependency:tree

mvn spring-boot:run 可以启动项目/ 可以打成war包进行部署  /也可以使用java -jar project.war(虽然是war包但是只要main方法还在就可以执行)
mvn -f F:\github\spring-cloud\spring-cloud-registry-one\pom.xml spring-boot:run 
mvn -f F:\github\spring-cloud\spring-cloud-registry-two\pom.xml spring-boot:run 
mvn -f F:\github\spring-cloud\spring-cloud-registry-three\pom.xml spring-boot:run
mvn -f F:\github\spring-cloud\spring-cloud-server\pom.xml spring-boot:run
mvn -f F:\github\spring-cloud\spring-cloud-client\pom.xml spring-boot:run

解压文件
jar -xvf exhibition-base-channel-impl.war  WEB-INF/classes/config.properties
替换文件
jar -uvf exhibition-base-channel-impl.war WEB-INF/classes/config.properties

http://127.0.0.1:1002/eureka/apps

----------------------------------------------------------tomcat部署注册中心--------------------------------------------------------------
服务器     tomcat1001
端口       1001             
war包名称  registerServer         
defaultZone: http://${security.user.name}:${security.user.password}@127.0.0.1:${server.port3}/registerServer/eureka/,http://${security.user.name}:${security.user.password}@127.0.0.1:${server.port2}/registerServer/eureka/ #在访问路径上加入用户名密码
EUREKA-SERVER-CLUSTER	n/a (3)	(3)	UP (3) - localhost:eureka-server-cluster:1003 , localhost:eureka-server-cluster:1002 , localhost:eureka-server-cluster:1001
registered-replicas	    http://127.0.0.1:1003/registerServer/eureka/, http://127.0.0.1:1002/registerServer/eureka/
unavailable-replicas	http://127.0.0.1:1003/registerServer/eureka/,http://127.0.0.1:1002/registerServer/eureka/

服务器     tomcat1002  
端口       1002        
war包名称  registerServer  
defaultZone: http://${security.user.name}:${security.user.password}@127.0.0.1:${server.port1}/registerServer/eureka/,http://${security.user.name}:${security.user.password}@127.0.0.1:${server.port3}/registerServer/eureka/ #在访问路径上加入用户名密码
EUREKA-SERVER-CLUSTER	n/a (3)	(3)	UP (3) - localhost:eureka-server-cluster:1003 , localhost:eureka-server-cluster:1002 , localhost:eureka-server-cluster:1001
registered-replicas	http://127.0.0.1:1003/registerServer/eureka/, http://127.0.0.1:1001/registerServer/eureka/
unavailable-replicas	http://127.0.0.1:1003/registerServer/eureka/,http://127.0.0.1:1001/registerServer/eureka/

服务器     tomcat1003  
端口       1003      
war包名称  registerServer    
defaultZone: http://${security.user.name}:${security.user.password}@127.0.0.1:${server.port1}/registerServer/eureka/,http://${security.user.name}:${security.user.password}@127.0.0.1:${server.port2}/registerServer/eureka/ #在访问路径上加入用户名密码
EUREKA-SERVER-CLUSTER	n/a (3)	(3)	UP (3) - localhost:eureka-server-cluster:1003 , localhost:eureka-server-cluster:1002 , localhost:eureka-server-cluster:1001
registered-replicas	http://127.0.0.1:1002/registerServer/eureka/, http://127.0.0.1:1001/registerServer/eureka/
unavailable-replicas	http://127.0.0.1:1002/registerServer/eureka/,http://127.0.0.1:1001/registerServer/eureka/
######################################################tomcat部署注册中心################################################################


编程式事务 请参看spring-cloud-servcie-impl-controller-test PersonServiceImpl



布隆过滤器(Bloom Filter):是一种空间效率极高的概率型算法和数据结构，用于判断一个元素是否在集合中（类似Hashset）
它的核心一个很长的二进制向量和一系列hash函数
数组长度以及hash函数的个数都是动态确定的。               
请参看spring-cloud-servcie-impl-controller-test BloomFilterTest
在的数据正确率100%不在的数据会有误伤率和fpp参数设置有关
Bloom Filter特点： 参考http://f.dataguru.cn/thread-184420-1-1.html
不存在漏报（False Negative），即某个元素在某个集合中，肯定能报出来。
可能存在误报（False Positive），即某个元素不在某个集合中，可能也被爆出来。



SSE技术详解：一种全新的HTML5服务器推送事件技术
一般来说，Web端即时通讯技术因受限于浏览器的设计限制，一直以来实现起来并不容易，主流的Web端即时通讯方案大致有4种：
传统Ajax短轮询、Comet技术、WebSocket技术、SSE（Server-sent Events）。关于这4种技术方式的优缺点，
请参考《Web端即时通讯技术盘点：短轮询、Comet、Websocket、SSE》。本文将专门讲解SSE技术。 
服务器推送事件（Server-sent Events），简称SSE，是 HTML 5 规范中的一个组成部分，可以用来从服务端实时推送数据到浏览器端。
相对于与之类似的 COMET 和 WebSocket 技术来说，服务器推送事件的使用更简单，对服务器端的改动也比较小。
对于某些类型的应用来说，服务器推送事件是最佳的选择。 
请参看 spring-cloud-servcie-impl-controller-test SseController(服务启动后直接在浏览器打开static/pages/sse1.html页面)
F12查看console可以看到只建立了一次连接,sse2,sse3每次都会建立新的连接


==========================消息队列=========================
kafka消息队列  com.ctl.test.controller.KafkaController /kafka/send  进行测试
    http://localhost:8080/kafka/send
集群模式(开始启动多个,然后相继宕机几个,只要有一个broker处于活动中就可以正常使用)
要保证__consumer_offsets的所有分区可用,offsets.topic.replication.factor至少配置为3
要保证集群所有的broker启动后,客户端再进行消费,否则会导致__consumer_offsets 主题的分区副本不能均匀分布到每个broker,这样一旦某个副本所在broker全挂掉,就不能消费了 
如果集群模式product不可以使用（可能是zookeeper脏数据造成的）
1、进入zookeeper 运行zkCli.sh 。
2、运行ls /brokers/topics 查看主题
3、然后运行 delete(deleteall) /brokers/topics/__consumer_offsets 删除__consumer_offsets_主题
4、然后重启kafka集群就好了。 
--------------------------------------------------------------------
activemq消息队列
    http://localhost:8080/activemq/sendMsg?msg=testmsg1
    http://localhost:8080/activemq/sendTopic?msg=testtopic1
 
    
    
==========================sharding-jdbc=========================
读写分离spring-cloud-sharding-jdbc
读 http://localhost:9090/member/selectById?id=1106202721666514946（从库）
写 http://localhost:9090/member/save (post/json)
{
	"name":"ctl",
	"mobile":"13161167381"
}

==========================zookeeper=========================
zookeeper 分布式锁
com.ctl.test.controller.ZookeeperLockController 测试controller
com.ctl.test.zookeeper.lock.CuratorUtil
http://localhost:18080/s/zookeeper/send 多次发送请求 如果上个业务还在处理中并没有释放锁(返回{"result":"can not get lock, exit job."})


==========================dubbo=========================
http://dubbo.apache.org/en-us/docs/user/quick-start.html
dubbo端口重复问题(springboot配置dubbo连接处于等待中,服务一直在启动中,则换一个zookeeper版本)
解决端口冲突的两种办法
//方法一：此时将端口改为其它端口都不起作用，可以将生产者端的端口改为-1，使用随机端口，可以解决问题
//<dubbo:provider protocol="dubbo" port="-1"/>
方法二：com.ctl.sharding.config.DynamicDubboPortReader 实现ApplicationContextAware动态绑定端口
com.ctl.sharding.controller.DubboController


==========================jvm虚拟机命令=========================
jps	显示系统中所有Hotspot虚拟机进程
jstat	收集Hotspot虚拟机各方面运行数据
jstack	显示虚拟机的线程栈信息
jinfo	显示虚拟机的配置信息
jmap	用于生成虚拟机的内存快照信息
---------------------------------------------------
jps -q   #只显示线程id  仅仅显示VM 标示，不显示jar,class, main参数等信息.
jps -m  #输出主函数传入的参数. 下的hello 就是在执行程序时从命令行输入的参数
jps -l     #输出应用程序主类完整package名称或jar完整名称.
jps -v    #列出jvm参数, -Xms20m -Xmx50m是启动程序指定的jvm参数
---------------------------------------------------
ps H -eo user,pid,ppid,tid,time,%cpu,cmd --sort=%cpu #打印用户、进程id、父进程id、线程id（对于此次很重要）、运行时间、CPU使用率、启动命令并按CPU使用率排序
---------------------------------------------------
jstack pid
Stack Trace for Java，用于生成虚拟机当前的线程快照信息，包含每一条线程的堆栈信息。该命令通常用于定位线程停顿原因，当出现线程停顿时，可通过stack查看每个线程的堆栈信息，进而分析停顿原因。 
命令格式: 
jstack [ option ] pid 
常用参数:
-l	除堆栈外，显示锁的附加信息
-F	当请求不被响应时，强制输出线程堆栈
-m	混合模式，打印java和本地C++调用的堆栈信息
---------------------------------------------------
jstat -gcutil pid 5s
每隔5s监控一次内存回收情况
S0和S1表示Survivor0和Survivor1，
E 代表 Eden 区使用率；
O（Old）代表老年代使用率    ；
P（Permanent）代表永久代使用率；
CCS 压缩使用比例
M 元空间(MetaspaceSize)已使用的占当前容量百分比
YGC（Young GC）代表Minor GC 次数；
YGCT代表Minor GC耗时；
FGC（Full GC）代表Full GC次数；
FGCT（Full GC）代表Full GC耗时；
GCT代表Minor & Full GC共计耗时。
#########################################################
class	类装载相关信息.
compiler	JIT编译器编译过的方法、耗时等.
gc	java堆信息和垃圾回收状况.
gccapacity	关注java堆各个区的最大和最小空间.
gccause	类似gcutil，额外输出导致上一次gc的原因.
gcnew	新生代gc状况.
gcnewcapacity	关注新生代gc使用的最大和最小空间.
gcold	老年代gc状况.
gcoldcapacity	关注老年代gc使用的最大和最小空间.
gcpermcapacity	关注持久代gc使用的最大和最小空间.
gcutil	关注已使用空间占总空间比例.
printcompilation	输出已经被JIT编译的方法.
-------------------------------------------------------
5. jmap 
Memory Map for Java，可以产生堆dump文件，查询堆和持久代的详细信息等。 
命令格式: 
jmap [ option ] pid 
执行示例： 
$ jmap -dump:format=b,file=dump.tmp 3700 
常用参数:
-dump	生成堆dump文件，格式为: -dump:[live,]format=b,file=<filename>
-heap	显示java堆的详细信息，包括垃圾回收期、堆配置和分代信息等
-histo	显示堆中对象的统计信息，包括类名称，对应的实例数量和总容量
-permstat	统计持久代中各ClassLoader的统计信息。


==========================memcached=========================
com.ctl.test.SpringbootMemcacheApplicationTests 测试类



==========================分布式事务=========================
pring-cloud-mybatis-atomikos
http://localhost:8080/atomikos/addMember (jdbc)
http://localhost:8080/atomikos/addMember2 (mybatis)



==========================spring-aop=========================
spring-cloud-servcie-impl-controller-test::com.ctl.test.controller.SpringAopController (为测试通过)
在Spring中创建切面，通过切面引入新功能-使用JAVA注解方式(无中生有)
com.ctl.test.sprinz.Test (测试通过)


==========================spring-cloud链路跟踪=========================
spring-cloud-registry-one
spring-cloud-registry-two
spring-cloud-registry-three
------------------------------------------
spring-cloud-sleuth-customer-ribbon
------------------------------------------
spring-cloud-sleuth-provider-ribbon
------------------------------------------
spring-cloud-zipkin(作为一个war或服务启动) -- com.linecorp.armeria.spring.ArmeriaAutoConfiguration 
端口写死8080 DEFAULT_PORT = (new Port()).setPort(8080).setProtocol(SessionProtocol.HTTP);启动zipkin服务时8080不能占用
------------------------------------------
java -jar zipkin-server-2.12.8-exec.jar --server.port=8081  （http://localhost:8081/zipkin/  直接下载zipkin服务jar包然后直接启动）


==========================ELK+logback+kafka 搭建分布式日志分析平台=========================
spring-boot-elk-kafka-logback
参考博客https://blog.csdn.net/yp090416/article/details/81589174
环境及war包
elasticsearch-6.7.1
kibana-6.7.1
logstash-6.7.1
zookeeper-3.5.3-beta
kafka_2.12-1.1.1
spring-boot版本<version>2.1.3.RELEASE</version>
<!--kafka依赖-->
<!-- https://mvnrepository.com/artifact/org.springframework.kafka/spring-kafka -->
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
    <version>2.2.5.RELEASE</version>
</dependency>

<!--logback-kafka-appender依赖-->
<!-- https://mvnrepository.com/artifact/com.github.danielwegener/logback-kafka-appender -->
<dependency>
    <groupId>com.github.danielwegener</groupId>
    <artifactId>logback-kafka-appender</artifactId>
    <version>0.2.0-RC2</version>
</dependency>

<!-- https://mvnrepository.com/artifact/net.logstash.logback/logstash-logback-encoder -->
<dependency>
    <groupId>net.logstash.logback</groupId>
    <artifactId>logstash-logback-encoder</artifactId>
    <version>5.3</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>	


==========================dubbo链路跟踪=========================
项目spring-boot-dubbo-consumer
类com.ctl.springboottest.SpringBootTestApplication
详细配置看上面类中的注释

==========================Linux平台Java调用so库-JNI使用例子=========================
F:\github\spring-cloud\javaagent  HSLienceProvider.java
参考地址https://blog.csdn.net/rocgege/article/details/58585225

F:\github\spring-cloud\javase-test\src\main\java\com\ctl\jni



==========================springboot注解=========================
F:\github\spring-cloud\spring-boot-test\src\main\java\com\ctl\springboottest\config\RedisConfig.java
@PropertySource("classpath:config.properties") 加载指定的配置文件
@ConditionalOnProperty(name = "redis.type", havingValue = "1") 当配置文件满足条件时执行
@ImportResource(locations = {"classpath:spring-dubbo.xml","classpath:spring-mybatis.xml"}) 引用多个配置文件


==========================maven配置=========================
F:\github\spring-cloud\spring-boot-test\pom.xml
Maven 配置 Tomcat8+ 部署方案
Maven配置多个镜像（切换仓库下载Jar包）

==========================tomcat8war包卸载=========================
F:\github\spring-cloud\javase-test\src\main\java\com\ctl\tomcat8\TomcatTest.java


==========================连接sftp=========================
F:\github\spring-cloud\javase-test\src\main\java\com\ctl\ftp\FtpsFileList.java

==========================maven自定义插件=========================
F:\github\spring-cloud\sftpmaven\src\main\java\com\ctl\maven\SftpMavenMojo.java
F:\github\spring-cloud\sftpmaven\pom.xml


==========================fastdfs文件上传=========================
com.ctl.test.controller.FastdfsFileUploadController