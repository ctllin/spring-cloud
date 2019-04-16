package com.ctl.test.javaapi.collection;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.*;

/**
 * 消息体定义 实现Delayed接口就是实现两个方法即compareTo 和 getDelay最重要的就是getDelay方法，这个方法用来判断是否到期……
 *
 * @author ctl
 * @date 20190320
 */
class DelayedMessage<T> implements Delayed {
    private T body; // 消息内容
    private long excuteTime;// 延迟时长，这个是必须的属性因为要按照这个判断延时时长。


    public T getBody() {
        return body;
    }

    public long getExcuteTime() {
        return excuteTime;
    }

    public DelayedMessage(T body, long delayTime) {
        this.body = body;
        this.excuteTime = delayTime + System.currentTimeMillis();
    }

    // 自定义实现比较方法返回 1 0 -1三个参数
    @Override
    public int compareTo(Delayed delayed) {
        if (this == delayed || !(delayed instanceof DelayedMessage)) {
            return 0;
        }
        DelayedMessage tmp = (DelayedMessage) delayed;
        if (this.excuteTime > tmp.getExcuteTime()) {
            return 1;
        } else {
            return -1;
        }
    }

    // 延迟任务是否到时就是按照这个方法判断如果返回的是负数则说明到期否则还没到期
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.excuteTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public String toString() {
        return this.getBody().toString();
    }
}


class DelayedConsumer implements Runnable {
    // 延时队列 ,消费者从其中获取消息进行消费
    private DelayQueue<DelayedMessage> queue;

    public DelayedConsumer(DelayQueue<DelayedMessage> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //take()
                DelayedMessage take = queue.take();
                System.out.println(" 消息体：" + take.getBody());
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class DelayedQueueTest {
    public static void main(String[] args) {
        // 创建延时队列
        DelayQueue<DelayedMessage> queue = new DelayQueue<>();
        Set<Long> longSet = new TreeSet<>();
        for (int i = 0; i < 5; i++) {
            long delayTime = new Random().nextInt(1000);
            longSet.add(delayTime);
            queue.offer(new DelayedMessage("test\t" + i + "\t" + delayTime, delayTime));
        }
        System.out.println(longSet);
        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间
        ExecutorService exec = Executors.newFixedThreadPool(1);
        exec.execute(new DelayedConsumer(queue));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exec.shutdown();
//        执行结果
//        [671, 692, 856, 866, 995]
//        消息体：test	0	671
//        消息体：test	2	692
//        消息体：test	4	856
//        消息体：test	1	866
//        消息体：test	3	995
    }
}
