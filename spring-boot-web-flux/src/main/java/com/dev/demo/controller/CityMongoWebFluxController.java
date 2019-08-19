package com.dev.demo.controller;

import com.alibaba.fastjson.JSON;
import com.dev.demo.bean.City;
import com.dev.demo.mongo.handler.CityMongoHandler;
import com.dev.demo.webflux.hanlder.CityHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <p>Title: CityWebFluxController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-19 14:34
 */
@RestController
@RequestMapping(value = "/mongo/city")
public class CityMongoWebFluxController {
    private static final Logger logger = LoggerFactory.getLogger(CityMongoWebFluxController.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CityMongoHandler cityMongoHandler;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Mono<City> findCityById(@PathVariable("id") Long id) {
        return cityMongoHandler.findCityById(id);
    }

    @GetMapping()
    @ResponseBody
    public Flux<City> findAllCity() {
        return cityMongoHandler.findAllCity();
    }

    @PostMapping()
    @ResponseBody
    public Mono<City> saveCity(@RequestBody City city) {
        Long increment = redisTemplate.opsForValue().increment(JSON.toJSONString(city),1); //指定自增值
        logger.info("increment={},city={}",increment, JSON.toJSONString(city));
        return cityMongoHandler.save(city);
    }

    @PutMapping()
    @ResponseBody
    public Mono<City> modifyCity(@RequestBody City city) {
        return cityMongoHandler.modifyCity(city);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        return cityMongoHandler.deleteCity(id);
    }
}