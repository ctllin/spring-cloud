package com.dev.demo.mongo.handler;

import com.dev.demo.bean.City;
import com.dev.demo.mongo.repository.CityMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: CityHandler</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-19 15:04
 */
@Component
public class CityMongoHandler {
    @Autowired
    private CityMongoRepository cityMongoRepository;

    public Mono<City> save(City city) {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
        }
        return cityMongoRepository.save(city);
    }

    public Mono<City> findCityById(Long id) {

        return cityMongoRepository.findById(id);
    }

    public Flux<City> findAllCity() {
        return cityMongoRepository.findAll();
    }

    public Mono<City> modifyCity(City city) {

        return cityMongoRepository.save(city);
    }
    public Mono<Long> deleteCity(Long id) {
        cityMongoRepository.deleteById(id);
        return Mono.create(cityMonoSink -> cityMonoSink.success(id));
    }
}
