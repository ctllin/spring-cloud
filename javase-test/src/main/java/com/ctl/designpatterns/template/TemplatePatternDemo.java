package com.ctl.designpatterns.template;

/**
 * com.ctl.designpatterns.template
 * dd 模板模式
 * 在模板模式（Template Pattern）中，一个抽象类公开定义了执行它的方法的方式/模板。它的子类可以按需要重写方法实现，但调用将以抽象类中定义的方式进行。这种类型的设计模式属于行为型模式。
 * ctl 2019/4/1 22:01
 */
//
//步骤 1
//创建一个抽象类，它的模板方法被设置为 final。
//Game.java
abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    //模板
    public final void play(){

        //初始化游戏
        initialize();

        //开始游戏
        startPlay();

        //结束游戏
        endPlay();
    }
}

//步骤 2
//创建扩展了上述类的实体类。
//Cricket.java
class Cricket extends Game {

    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }
}

//Football.java
class Football extends Game {

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }
}

//步骤 3
//使用 Game 的模板方法 play() 来演示游戏的定义方式。
//TemplatePatternDemo.java
public class TemplatePatternDemo {
    public static void main(String[] args) {

        Game game = new Cricket();
        game.play();
        System.out.println("---------------------------------------------------------");
        game = new Football();
        game.play();
    }
}
//Cricket Game Initialized! Start playing.
//Cricket Game Started. Enjoy the game!
//Cricket Game Finished!
//---------------------------------------------------------
//Football Game Initialized! Start playing.
//Football Game Started. Enjoy the game!
//Football Game Finished!