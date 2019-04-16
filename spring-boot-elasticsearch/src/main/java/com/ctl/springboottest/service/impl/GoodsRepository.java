package com.ctl.springboottest.service.impl;

import com.ctl.springboottest.po.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * com.ctl.springboottest
 * GoodsRepository
 * ctl 2019/4/14 15:56
 */
@Component
public interface GoodsRepository extends ElasticsearchRepository<Goods,String> {
    //该方法没有实现却可以直接使用,暂时未理解 请参看GoodsRepository.png
    Goods queryGoodsById(String id);
    //该方法没有实现却可以直接使用,暂时未理解 请参看GoodsRepository.png
    List<Goods> queryGoodsByName(String name);
}
