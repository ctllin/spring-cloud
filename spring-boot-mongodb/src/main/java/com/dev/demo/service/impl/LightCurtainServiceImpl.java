package com.dev.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.dev.demo.controller.LightCurtainController;
import com.dev.demo.model.constant.LightCurtainConstant;
import com.dev.demo.model.sensor.LightCurtainData;
import com.dev.demo.model.sensor.LightCurtainJsonRootBean;
import com.dev.demo.service.LightCurtainRepository;
import com.dev.demo.service.LightCurtainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public void save(LightCurtainJsonRootBean record) {
        if (record == null || record.getLightCurtainData() == null) {
            return;
        }
        LightCurtainData lightCurtainData = record.getLightCurtainData();
        lightCurtainData.setDeviceId(record.getDeviceId());
        LightCurtainData save = lightCurtainRepository.save(lightCurtainData);
        logger.info(JSON.toJSONString(save));
    }

    @Override
    public List<LightCurtainData> findByDeviceId(String deviceId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "timestamp");
        Pageable pageable = PageRequest.of(LightCurtainConstant.CURRENTPAGE, LightCurtainConstant.PAGESIZE, sort);
        Criteria criteria = new Criteria();
        Query query = new Query();
        query.with(pageable);
        query.with(sort);
        criteria.and("deviceId").equals(deviceId);
        query.addCriteria(criteria);
        List<LightCurtainData> records = mongoTemplate.find(query, LightCurtainData.class);
        return records;
    }

    @Override
    public List<LightCurtainData> findBySensorId(String sensorId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "timestamp");
        Pageable pageable = PageRequest.of(LightCurtainConstant.CURRENTPAGE, LightCurtainConstant.PAGESIZE, sort);
        Criteria criteria = new Criteria();
        Query query = new Query();
        query.with(pageable);
        query.with(sort);
        criteria.and("sensorId").equals(sensorId);
        query.addCriteria(criteria);
        List<LightCurtainData> records = mongoTemplate.find(query, LightCurtainData.class);
        return records;
    }
}
