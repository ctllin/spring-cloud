package com.ctl.springboottest.service.impl;

import com.ctl.springboottest.po.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * com.ctl.springboottest
 * GoodsRepository
 * ctl 2019/4/14 15:56
 */
@Component
public interface GoodsRepository extends ElasticsearchRepository<Goods,String> {
    Goods queryGoodsById(String id);
}
