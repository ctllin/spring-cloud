package com.ctl.designpatterns.chain;

/**
 * com.ctl.designpatterns.chain
 * f
 * ctl 2019/4/1 21:40
 */
//步骤 1
//创建抽象的记录器类。
//AbstractLogger.java
abstract class AbstractLogger {
    static int INFO = 1;
    static int DEBUG = 2;
    static int ERROR = 3;
    protected int level;
    //责任链中的下一个元素
    protected AbstractLogger nextLogger;

    void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    abstract protected void write(String message);

}

//步骤 2
//创建扩展了该记录器类的实体类。
//ConsoleLogger.java
class ConsoleLogger extends AbstractLogger {

    ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}

//ErrorLogger.java
class ErrorLogger extends AbstractLogger {

    ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}

//FileLogger.java
class FileLogger extends AbstractLogger {

    FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}


//步骤 3
//创建不同类型的记录器。赋予它们不同的错误级别，并在每个记录器中设置下一个记录器。每个记录器中的下一个记录器代表的是链的一部分。
//ChainPatternDemo.java
public class ChainPatternDemo {
    private static AbstractLogger getChainOfLoggers() {
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);
        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);
        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");
        System.out.println("---------------------------------------------------------");
        loggerChain.logMessage(AbstractLogger.DEBUG, "This is a debug level information.");
        System.out.println("---------------------------------------------------------");
        loggerChain.logMessage(AbstractLogger.ERROR, "This is an error information.");
    }
}

//Standard Console::Logger: This is an information.
//---------------------------------------------------------
//File::Logger: This is a debug level information.
//Standard Console::Logger: This is a debug level information.
//---------------------------------------------------------
//Error Console::Logger: This is an error information.
//File::Logger: This is an error information.
//Standard Console::Logger: This is an error information.

//责任链模式
//顾名思义，责任链模式（Chain of Responsibility Pattern）为请求创建了一个接收者对象的链。这种模式给予请求的类型，对请求的发送者和接收者进行解耦。这种类型的设计模式属于行为型模式。
//在这种模式中，通常每个接收者都包含对另一个接收者的引用。如果一个对象不能处理该请求，那么它会把相同的请求传给下一个接收者，依此类推。