import com.alibaba.fastjson.JSON;
import com.dev.demo.bean.City;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * CityWebFluxController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Aug 19, 2019</pre>
 */


public class CityWebFluxControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(CityWebFluxControllerTest.class);

    private WebClient client = null;

    @Before
    public void before() throws Exception {
        client = WebClient.create("http://127.0.0.1:8080/mongo");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: findCityById(@PathVariable("id") Long id)
     */
    @Test
    public void testFindCityById() throws Exception {
        Mono<City> result = client.get()// 请求方法,get,post...
                .uri("/city/{id}", "1566197240092")// 请求相对地址以及参数
                .accept(MediaType.APPLICATION_JSON).retrieve()// 请求类型
                .bodyToMono(City.class);// 返回类型
        City block = result.block();
        logger.info(JSON.toJSONString(block));


    }

    /**
     * Method: findAllCity()
     */
    @Test
    public void testFindAllCity() throws Exception {
        Flux<City> result = client.get()// 请求方法,get,post...
                .uri("/city")// 请求相对地址以及参数
                .accept(MediaType.APPLICATION_JSON).retrieve()// 请求类型
                .bodyToFlux(City.class);// 返回类型
        List<City> block = result.collectList().block();
        logger.info(JSON.toJSONString(block));

    }

    /**
     * Method: saveCity(@RequestBody City city)
     */
    @Test
    public void testSaveCity() throws Exception {
        City city = new City();
        Mono<City> result = client.post()// 请求方法,get,post...
                .uri("/city")// 请求相对地址以及参数
                .syncBody(city)
                .accept(MediaType.APPLICATION_JSON).retrieve()// 请求类型
                .bodyToMono(City.class);// 返回类型
        City block = result.block();
        logger.info(JSON.toJSONString(block));

    }

    /**
     * Method: modifyCity(@RequestBody City city)
     */
    @Test
    public void testModifyCity() throws Exception {
        City city = new City();
        Mono<City> result = client.post()// 请求方法,get,post...
                .uri("/city")// 请求相对地址以及参数
                .syncBody(city)
                .accept(MediaType.APPLICATION_JSON).retrieve()// 请求类型
                .bodyToMono(City.class);// 返回类型
        City block = result.block();
        logger.info(JSON.toJSONString(block));
    }

    /**
     * Method: deleteCity(@PathVariable("id") Long id)
     */
    @Test
    public void testDeleteCity() throws Exception {
        City city = new City();
        Mono<Long> result = client.delete()// 请求方法,get,post...
                .uri("/city/1000000")// 请求相对地址以及参数
                .accept(MediaType.APPLICATION_JSON).retrieve()// 请求类型
                .bodyToMono(Long.class);// 返回类型
        Long block = result.block();
        logger.info(block+"");
    }


} 
