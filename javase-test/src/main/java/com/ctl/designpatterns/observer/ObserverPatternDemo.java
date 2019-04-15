package com.ctl.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * com.ctl.designpatterns.observer
 * d
 * ctl 2019/4/1 21:34
 */
//步骤 1
//创建 Subject 类。
//Subject.java
class Subject {

    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

//步骤 2
//创建 Observer 类。
//Observer.java
abstract class Observer {
    protected Subject subject;
    public abstract void update();
}

//步骤 3
//创建实体观察者类。
//BinaryObserver.java
class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Binary String: " + Integer.toBinaryString( subject.getState() ) );
    }
}

//OctalObserver.java
class OctalObserver extends Observer{

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Octal String: "+ Integer.toOctalString( subject.getState() ) );
    }
}

//HexaObserver.java
class HexaObserver extends Observer{

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: "+ Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}

//步骤 4
//使用 Subject 和实体观察者对象。
//ObserverPatternDemo.java
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
//观察者模式
//当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。比如，当一个对象被修改时，则会自动通知它的依赖对象。观察者模式属于行为型模式。
