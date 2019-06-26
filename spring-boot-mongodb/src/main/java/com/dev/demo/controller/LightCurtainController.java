package com.dev.demo.controller;

import com.dev.demo.model.query.LightCurtainDataQuery;
import com.dev.demo.model.sensor.LightCurtainJsonRootBean;
import com.dev.demo.service.LightCurtainService;
import com.hanshow.wise.common.jo.BaseDTO;
import com.hanshow.wise.common.jo.BaseQUERY;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/lightCurtain")
public class LightCurtainController {
    private static Logger logger = LoggerFactory.getLogger(LightCurtainController.class);
    @Autowired
    private LightCurtainService lightCurtainService;

    @RequestMapping(value = "/message/receive", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public Object receive(HttpServletRequest request, @RequestBody BaseQUERY<LightCurtainJsonRootBean> record) {
        lightCurtainService.save(record.getData());
        return BaseDTO.genSucBaseDTO(record, record.getData());
    }

    @RequestMapping(value = "/message/getByDeviceId", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public Object getByDeviceId(HttpServletRequest request, @RequestBody BaseQUERY<LightCurtainDataQuery> query) {
        return lightCurtainService.findByDeviceId(query.getData().getDeviceId());
    }

    @RequestMapping(value = "/message/getBySensorId", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public Object getBySensorId(HttpServletRequest request, @RequestBody BaseQUERY<LightCurtainDataQuery> query) {
        return lightCurtainService.findBySensorId(query.getData().getSensorId());
    }
}
