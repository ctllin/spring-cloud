package com.hanshow.wise.base.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hanshow.wise.base.data.model.constant.CurtainConstant;
import com.hanshow.wise.base.data.model.sensor.LightCurtainData;
import com.hanshow.wise.base.data.model.sensor.LightCurtainJsonRootBean;
import com.hanshow.wise.base.data.service.LightCurtainRepository;
import com.hanshow.wise.base.data.service.LightCurtainService;
import com.hanshow.wise.base.device.model.dto.DeviceInfoIncuTagDTO;
import com.hanshow.wise.base.device.model.query.DeviceCheckExistQUERY;
import com.hanshow.wise.base.device.service.DeviceService;
import com.hanshow.wise.base.esl.model.dto.DeviceGoodsPrDTO;
import com.hanshow.wise.base.esl.model.query.DeviceGoodsPrQUERY;
import com.hanshow.wise.base.esl.service.EslService;
import com.hanshow.wise.common.jo.BaseDTO;
import com.hanshow.wise.common.jo.BaseQUERY;
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
import java.util.Map;

@Service
public class LightCurtainServiceImpl implements LightCurtainService {
    private static Logger logger = LoggerFactory.getLogger(LightCurtainServiceImpl.class);

    @Autowired
    private LightCurtainRepository lightCurtainRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired(required = false)
    private DeviceService deviceService;
    @Autowired(required = false)
    private EslService eslService;
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
            BaseDTO<DeviceInfoIncuTagDTO> deviceByCodeDTO = deviceService.getDeviceByCode(query, record.getData().getDeviceId());
            if(deviceByCodeDTO.success()){
                DeviceGoodsPrQUERY deviceGoodsPrQUERY = new DeviceGoodsPrQUERY();
                query.setMerchantId(deviceByCodeDTO.getData().getMarchantId());
                deviceGoodsPrQUERY.setDeviceCode(record.getData().getDeviceId());
                deviceGoodsPrQUERY.setStoreId(deviceByCodeDTO.getData().getStoreId());
                BaseDTO<Map<String, List<DeviceGoodsPrDTO>>> deviceGoodsPrListDTO = eslService.getDeviceGoodsPrList(query, deviceGoodsPrQUERY);
                if(deviceGoodsPrListDTO.success()){
                    List<DeviceGoodsPrDTO> deviceGoodsPrDTOS = deviceGoodsPrListDTO.getData().get(deviceGoodsPrListDTO.getDataKey());
                    logger.info(JSONArray.toJSONString(deviceGoodsPrDTOS));

                }
            }
            logger.info(JSON.toJSONString(deviceByCodeDTO));
        } catch (Exception e) {
            logger.error("调用设备接口获取设备信息失败", e);
        }

        logger.info(JSON.toJSONString(save));
    }

    @Override
    public List<LightCurtainData> findByDeviceId(String deviceId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "timestamp");
        Pageable pageable = PageRequest.of(CurtainConstant.CURRENTPAGE - 1, CurtainConstant.PAGESIZE, sort);
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
        Pageable pageable = PageRequest.of(CurtainConstant.CURRENTPAGE - 1, CurtainConstant.PAGESIZE, sort);
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
