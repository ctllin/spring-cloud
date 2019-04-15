package com.ctl.test;

import com.ctl.test.configuration.JedisClusterConfig;
import net.sf.json.JSONObject;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.exceptions.JedisNoReachableClusterNodeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: GlobalException</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-03-09 16:28
 */
@ControllerAdvice
public class DefaultExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        response.setStatus(HttpStatus.OK.value()); //设置状态码
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
        response.setCharacterEncoding("UTF-8"); //避免乱码
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            if (response.getWriter().checkError()) {
                //关闭浏览器,或管理页面需要退出该死循环
                logger.info("客户端断开连接");
                modelAndView.addObject("errMess", "客户端断开连接");
                return modelAndView;
            }
            Map<String, Object> result = new HashMap<>();
            result.put("errMess", exception);
            response.getWriter().write(JSONObject.fromObject(result).toString());
            logger.error("The-global-exception-handler-mes:", exception);
        } catch (ClientAbortException e) {
            logger.error("远程主机强迫关闭了一个现有的连接", e);
            modelAndView.addObject("errMess", "远程主机强迫关闭了一个现有的连接");
            return modelAndView;
        } catch (Exception e) {
            modelAndView.addObject("errMess", "全局异常处理失败");
            logger.error("全局异常处理失败", e);
        }
        return modelAndView;
    }
}
