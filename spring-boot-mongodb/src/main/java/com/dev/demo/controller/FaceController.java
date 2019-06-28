package com.dev.demo.controller;

import com.dev.demo.model.query.FaceInfoQuery;
import com.dev.demo.model.query.FaceStatQuery;
import com.dev.demo.model.query.LightCurtainDataQuery;
import com.dev.demo.model.sensor.FaceJsonRootBean;
import com.dev.demo.model.sensor.LightCurtainJsonRootBean;
import com.dev.demo.service.FaceService;
import com.dev.demo.service.LightCurtainService;
import com.hanshow.wise.common.jo.BaseDTO;
import com.hanshow.wise.common.jo.BaseQUERY;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/face")
public class FaceController {
    private static Logger logger = LoggerFactory.getLogger(FaceController.class);
    @Autowired
    private FaceService faceService;

    @RequestMapping(value = "/message/receive", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public Object receive(HttpServletRequest request, @RequestBody BaseQUERY<FaceJsonRootBean> record) {
        faceService.save(record);
        return BaseDTO.genSucBaseDTO(record, record.getData());
    }

    @RequestMapping(value = "/faceInfo/getByDeviceId", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public Object getFaceInfoByDeviceId(HttpServletRequest request, @RequestBody BaseQUERY<FaceInfoQuery> query) {
        return faceService.getFaceInfoByDeviceId(query.getData().getDeviceId());
    }

    @RequestMapping(value = "/faceStat/getByDeviceId", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public Object getFaceStatByDeviceId(HttpServletRequest request, @RequestBody BaseQUERY<FaceStatQuery> query) {
        return faceService.getFaceStatByDeviceId(query.getData().getDeviceId());
    }
}
