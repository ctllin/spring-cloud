package com.ctl.designpatterns.flyweight;


import java.util.HashMap;

//步骤 1
//创建一个接口。
//Shape.java
interface Shape {
    void draw();
}

//步骤 2
//创建实现接口的实体类。
//Circle.java
class Circle implements Shape {
    private String color;
    private int x;
    private int y;
    private int radius;

    public Circle(String color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color + ", x : " + x + ", y :" + y + ", radius :" + radius);
    }
}

//步骤 3
//创建一个工厂，生成基于给定信息的实体类的对象。
//ShapeFactory.java
class ShapeFactory {
    private static final HashMap<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle) circleMap.get(color);
        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}

//步骤 4
//使用该工厂，通过传递颜色信息来获取实体类的对象。
//FlyweightPatternDemo.java
/**
 * com.ctl.designpatterns.flyweight
 * Test 享元模式
 * ctl 2019/4/1 20:54
 */
public class FlyweightPatternDemo {
    private static final String colors[] ={"Red", "Green", "Blue", "White", "Black"};

    public static void main(String[] args) {

        for (int i = 0; i < 20; ++i) {
            Circle circle = (Circle) ShapeFactory.getCircle(getRandomColor());
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }

    private static String getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    private static int getRandomX() {
        return (int) (Math.random() * 100);
    }

    private static int getRandomY() {
        return (int) (Math.random() * 100);
    }
}
//享元模式
//享元模式（Flyweight Pattern）主要用于减少创建对象的数量，以减少内存占用和提高性能。这种类型的设计模式属于结构型模式，它提供了减少对象数量从而改善应用所需的对象结构的方式。
//享元模式尝试重用现有的同类对象，如果未找到匹配的对象，则创建新对象。我们将通过创建 5 个对象来画出 20 个分布于不同位置的圆来演示这种模式。由于只有 5 种可用的颜色，
//所以 color 属性被用来检查现有的 Circle 对象。
//
//原型模式：减少类class的数量，用对象来代替类。　　（这些对象内部属性一致）
//享元模式：减少对象的数量