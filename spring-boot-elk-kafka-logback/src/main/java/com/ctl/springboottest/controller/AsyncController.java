package com.ctl.springboottest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import com.ctl.springboottest.service.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * com.ctl.test.controller
 * AsyncController
 * ctl 2019/3/31 21:35
 */
@RestController
public class AsyncController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TaskService taskService;
    private final ExecutorService executorService = Executors.newFixedThreadPool(50);

    @Autowired
    public AsyncController(TaskService taskService) {
        this.taskService = taskService;
    }


    //    阻塞的Controller
//    在这个例子中，一个请求到达控制器。servlet线程不会被释放，直到长时间运行的方法被执行，我们退出
    @RequestMapping(value = "/block", method = RequestMethod.GET, produces = "text/html")
    public String executeSlowTask() {
        logger.info("Request received");
        String result = taskService.execute();
        logger.info("Servlet thread released");
        return result;
    }

    //https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-async
//    官方文档中说DeferredResult和Callable都是为了异步生成返回值提供基本的支持。简单来说就是一个请求进来，
//    如果你使用了DeferredResult或者Callable，在没有得到返回数据之前，DispatcherServlet和所有Filter就会退出Servlet容器线程，
//    但响应保持打开状态，一旦返回数据有了，这个DispatcherServlet就会被再次调用并且处理，以异步产生的方式，向请求端返回值。
//    这么做的好处就是请求不会长时间占用服务连接池，提高服务器的吞吐量
    @ResponseBody
    @RequestMapping("/async01")
    public Callable<Object> async01() {
        long start = System.currentTimeMillis();
        System.out.println("主线程开始..." + Thread.currentThread() + "==>" + start);
        Callable<Object> callable = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                long start1 = System.currentTimeMillis();
                System.out.println("子线程开始..." + Thread.currentThread() + "==>" + start1);
                Thread.sleep(2000);
                long end1 = System.currentTimeMillis();
                System.out.println("子线程结束..." + Thread.currentThread() + "==>" + end1 + "\t" + (end1 - start1));
                return "Callable<Object> async01()";
            }
        };
        long end = System.currentTimeMillis();
        System.out.println("主线程结束..." + Thread.currentThread() + "==>" + end + "\t" + (end - start));
        return callable;
    }


    //    返回DeferredResult
//    首先，我们需要创建一个deferredresult对象。此对象将由控制器返回。我们将完成和Callable相同的事，当我们在另一个线程处理长时间运行的任务的时候释放servlet线程。
    @ResponseBody
    @RequestMapping("/async02")
    public DeferredResult<Object> async02() {
        DeferredResult<Object> deferredResult = new DeferredResult<>((long) 3000, "create fail...");
        DeferredResultQueue.save(deferredResult);
        return deferredResult;
    }


}
