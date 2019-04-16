package com.ctl.designpatterns.builder;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * description:
 * 自行车生产线接口
 * Created by chenzanjin on 2017/2/22.
 */
interface BikeBuilder {
    // 组装轮胎
    public void buildTyres();

    // 组装车架
    public void buildFrame();

    // 组装GPS定位装置
    public void buildGPS();

    // 获取自行车
    public Bike getBike();
}


//2、定义摩拜单车生产线

/**
 * description:
 * 摩拜单车生产线
 * Created by chenzanjin on 2017/2/22.
 */
class MoBikeBuilder implements BikeBuilder {
    // 拥有单车对象
    Bike bike = new Bike();

    @Override
    public void buildTyres() {
        bike.setTyre("橙色轮胎");
    }

    @Override
    public void buildFrame() {
        bike.setFrame("橙色车架");
    }

    @Override
    public void buildGPS() {
        bike.setGps("mobike定制版GPS定位装置");
    }

    @Override
    public Bike getBike() {
        return bike;
    }
}


//3、定义ofo单车生产线
/**
 * description:
 * ofo单车生产线
 * Created by chenzanjin on 2017/2/22.
 */
class OfoBikeBuilder implements BikeBuilder {
    // 拥有单车对象
    Bike bike = new Bike();

    @Override
    public void buildTyres() {
        bike.setTyre("黑色轮胎");
    }

    @Override
    public void buildFrame() {
        bike.setFrame("黄色车架");
    }

    @Override
    public void buildGPS() {
        bike.setGps("ofo定制版GPS定位装置");
    }

    @Override
    public Bike getBike() {
        return bike;
    }
}


//4、定义单车对象
/**
 * description:
 * 自行车对象
 * Created by chenzanjin on 2017/2/22.
 */
class Bike {
    // 轮胎
    private String tyre;
    // 车架
    private String frame;
    // GPS定位装置
    private String gps;

    public String getTyre() {
        return tyre;
    }

    public void setTyre(String tyre) {
        this.tyre = tyre;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }
}

//5、定义工程部
/**
 * description：
 * 工程部门作为指挥者，可以指导生产部门作业
 * Created by Administrator on 2017/2/22.
 */
class EngineeringDepartment {
    // 用户告知指挥者想要什么样的单车
    BikeBuilder bikeBuilder;

    public EngineeringDepartment(BikeBuilder bikeBuilder) {
        this.bikeBuilder = bikeBuilder;
    }

    // 指导组装单车
    public void Construct() {
        bikeBuilder.buildTyres();
        bikeBuilder.buildFrame();
        bikeBuilder.buildGPS();
    }
}

//6、测试类
/**
 * description:
 * 建造者测试类
 * Created by Administrator on 2017/2/22.
 */
public class Test {
    public static void main(String[] args) {
        // 建造摩拜单车
        BikeBuilder moBikeBuilder = new MoBikeBuilder();
        EngineeringDepartment ed1 = new EngineeringDepartment(moBikeBuilder);
        ed1.Construct();// 指导组装
        // 产出单车，体现建造和显示分离
        Bike moBike = moBikeBuilder.getBike();
        System.out.println(JSON.toJSON(moBike));
        // 建造ofo单车
        BikeBuilder ofoBikeBuilder = new OfoBikeBuilder();
        EngineeringDepartment ed2 = new EngineeringDepartment(ofoBikeBuilder);
        ed2.Construct();// 指导组装
        Bike ofoBike = ofoBikeBuilder.getBike();
        System.out.println(JSON.toJSON(ofoBike));

    }
}