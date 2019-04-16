package com.ctl.test.controller;

import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

/**
 * 只有push1做到了客户端一次请求,服务端不断提供数据
 * @author ctl
 */
@RestController
@RequestMapping(value = "/sse")
public class SseController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    private WebApplicationContext applicationContext;
    @RequestMapping(value = "push1")
    public  void push(HttpServletRequest request, HttpServletResponse response) {
        //设置状态码
        response.setStatus(HttpStatus.OK.value());
        //设置ContentType
        response.setContentType(MediaType.TEXT_EVENT_STREAM_VALUE);
        try {
            PrintWriter writer = response.getWriter();
            Random random = new Random();
            while (true){
                //writer.write("event: slide\n"); // 事件类型
                writer.write("id: "+System.currentTimeMillis()+"\n");
                // 消息数据
                writer.write("data:sse测试2\t" + StringUtils.leftPad("" + random.nextInt(10000000), 9, "0") + "------" + request.toString() + "\n");
                // 重连时间
                writer.write("retry: 5000\n");
                // 消息结束
                writer.write("\n\n");
                writer.flush();
                Thread.sleep(500);
                if(System.currentTimeMillis()%2==0){
                    //为0时return,这时前端会执行断线重连
                    return;
                }
                if(writer.checkError()){
                    //关闭浏览器,或管理页面需要退出该死循环
                    logger.info("客户端断开连接");
                    return;
                }
            }
        } catch (Exception e) {
            logger.error("发送消息失败",e);
        }
//        logger.info("text_event_stream_value");
        //return "data:sse测试1\t" + random.nextInt() + "\n\n";
    }

    @RequestMapping(value = "push2",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public  String push2(HttpServletRequest request, HttpServletResponse response) {
        Random random = new Random();
        logger.info("text_event_stream_value,request={},thread={}",request.toString(),Thread.currentThread());
        return "data:sse测试2\t" + StringUtils.leftPad("" + random.nextInt(10000000), 9, "0") + "------" + request.toString() + "\n\n";
    }

    @RequestMapping(value = "push3")
    public  String push3(HttpServletRequest request, HttpServletResponse response) {
        Random random = new Random();
        logger.info("text_event_stream_value,request={}",request.toString());
        return "data:sse测试3\t" + StringUtils.leftPad("" + random.nextInt(10000000), 9, "0") + "------" + request.toString() + "\n\n";
    }
    /**
     * http://localhost/sse/view
     * @return
     */
    @RequestMapping("view")
    public String view() {
        return "view";
    }

    /**
     * spring boot中获取所有RequestMapping的URL路径列表集
     * @return
     */
    @RequestMapping(value = "getAllUrl", method = RequestMethod.GET)
    public Object getAllUrl() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        List<Map<String, String>> list = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            Map<String, String> map1 = new HashMap<>();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                map1.put("url", url);
            }
            // 类名
            map1.put("className", method.getMethod().getDeclaringClass().getName());
            // 方法名
            map1.put("method", method.getMethod().getName());
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                map1.put("type", requestMethod.toString());
            }
            list.add(map1);
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray;
    }

}
