package com.ctl.designpatterns.decorator;

/**
 * com.ctl.designpatterns.decorator
 * DecoratorPatternDemo 装饰器模式
 装饰器模式（Decorator Pattern）允许向一个现有的对象添加新的功能，同时又不改变其结构。这种类型的设计模式属于结构型模式，它是作为现有的类的一个包装。
 这种模式创建了一个装饰类，用来包装原有的类，并在保持类方法签名完整性的前提下，提供了额外的功能。
 * ctl 2019/4/1 22:08
 */
//步骤 1
//创建一个接口：
//Shape.java
interface Shape {
    void draw();
}

//步骤 2
//创建实现接口的实体类。
//Rectangle.java
class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}

//Circle.java
class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
//步骤 3
//创建实现了 Shape 接口的抽象装饰类。
//ShapeDecorator.java
abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}

//步骤 4
//创建扩展了 ShapeDecorator 类的实体装饰类。
//RedShapeDecorator.java
class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}

//步骤 5
//使用 RedShapeDecorator 来装饰 Shape 对象。
//DecoratorPatternDemo.java
public class DecoratorPatternDemo {
    public static void main(String[] args) {

        Shape circle = new Circle();
        Shape rectangle = new Rectangle();

        Shape redCircle = new RedShapeDecorator(new Circle());
        Shape redRectangle = new RedShapeDecorator(new Rectangle());

//        System.out.println("Circle with normal border");
        circle.draw();
        System.out.println("---------------------------------------------------------");

//        System.out.println("Rectangle with normal border");
        rectangle.draw();
        System.out.println("---------------------------------------------------------");

//        System.out.println("\nCircle of red border");
        redCircle.draw();
        System.out.println("---------------------------------------------------------");

//        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}
//Shape: Circle
//---------------------------------------------------------
//Shape: Rectangle
//---------------------------------------------------------
//Shape: Circle
//Border Color: Red
//---------------------------------------------------------
//Shape: Rectangle
//Border Color: Red