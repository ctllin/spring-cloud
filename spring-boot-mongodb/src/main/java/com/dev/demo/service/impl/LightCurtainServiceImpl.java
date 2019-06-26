package com.dev.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.dev.demo.controller.LightCurtainController;
import com.dev.demo.model.constant.LightCurtainConstant;
import com.dev.demo.model.sensor.LightCurtainData;
import com.dev.demo.model.sensor.LightCurtainJsonRootBean;
import com.dev.demo.service.LightCurtainRepository;
import com.dev.demo.service.LightCurtainService;
import com.hanshow.wise.base.device.model.dto.DeviceInfoIncuTagDTO;
import com.hanshow.wise.base.device.model.query.DeviceCheckExistQUERY;
import com.hanshow.wise.base.device.service.DeviceService;
import com.hanshow.wise.common.jo.BaseDTO;
import com.hanshow.wise.common.jo.BaseQUERY;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LightCurtainServiceImpl implements LightCurtainService {
    private static Logger logger = LoggerFactory.getLogger(LightCurtainServiceImpl.class);

    @Autowired
    private LightCurtainRepository lightCurtainRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired(required = false)
    private DeviceService deviceService;
    @Override
    public void save(BaseQUERY<LightCurtainJsonRootBean> record) {
        if (record == null || record.getData()==null|| record.getData().getLightCurtainData() == null) {
            return;
        }
        LightCurtainData lightCurtainData = record.getData().getLightCurtainData();
        lightCurtainData.setDeviceId(record.getData().getDeviceId());
        LightCurtainData save = lightCurtainRepository.save(lightCurtainData);
        try {
            DeviceCheckExistQUERY deviceCheckExistQUERY = new DeviceCheckExistQUERY();
            deviceCheckExistQUERY.setDeviceCode(record.getData().getDeviceId());
            BaseQUERY<DeviceCheckExistQUERY> query = new BaseQUERY<>();
            query.setRequestId(record.getRequestId());
            query.setTimestamp(record.getTimestamp());
            query.setData(deviceCheckExistQUERY);
            BaseDTO<DeviceInfoIncuTagDTO> deviceByCode = deviceService.getDeviceByCode(query, record.getData().getDeviceId());
            logger.info(JSON.toJSONString(deviceByCode));
        } catch (Exception e) {
            logger.error("", e);
        }

        logger.info(JSON.toJSONString(save));
    }

    @Override
    public List<LightCurtainData> findByDeviceId(String deviceId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "timestamp");
        Pageable pageable = PageRequest.of(LightCurtainConstant.CURRENTPAGE - 1, LightCurtainConstant.PAGESIZE, sort);
        Query query = new Query();
        query.with(pageable);
        query.with(sort);
        Criteria criteria = new Criteria();
        criteria.and("deviceId").is(deviceId);
        query.addCriteria(criteria);
        List<LightCurtainData> records = mongoTemplate.find(query, LightCurtainData.class);
        return records;
    }

    @Override
    public List<LightCurtainData> findBySensorId(String sensorId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "timestamp");
        Pageable pageable = PageRequest.of(LightCurtainConstant.CURRENTPAGE - 1, LightCurtainConstant.PAGESIZE, sort);
        Criteria criteria = new Criteria();
        Query query = new Query();
        query.with(pageable);
        query.with(sort);
        criteria.and("sensorId").is(sensorId);
        query.addCriteria(criteria);
        List<LightCurtainData> records = mongoTemplate.find(query, LightCurtainData.class);
        return records;
    }
}
