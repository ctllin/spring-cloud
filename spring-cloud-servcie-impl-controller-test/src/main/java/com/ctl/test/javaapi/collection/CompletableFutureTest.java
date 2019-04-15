package com.ctl.test.javaapi.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.*;

/**
 * <p>Title: CompletableFutureTest</p>
 * <p>Description: Java8新特性整理之CompletableFuture：组合式、异步编程</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-03-25 10:32
 */
public class CompletableFutureTest {
    static final Logger logger = LoggerFactory.getLogger(CompletableFutureTest.class);

    /**
     * 无返回值
     *
     * @param executors
     * @throws Exception
     */
    public static void runAsync(ExecutorService executors) throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            logger.info("runAsync run end ...");
        }, executors);

        future.get();
    }

    /**
     * 有返回值
     *
     * @param executors
     * @throws Exception
     */
    public static void supplyAsync(ExecutorService executors) throws Exception {
        Long start = System.currentTimeMillis();
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            logger.info("supplyAsync run end ...");
            return System.currentTimeMillis();
        }, executors);
        long time = future.get();//阻塞等待
        Long end = System.currentTimeMillis();
        logger.info("time = {},wait-time={}", time, (end - start));
    }

    /**
     * 当CompletableFuture的计算结果完成，或者抛出异常的时候，可以执行特定的Action
     */
    public static void whenComplete(ExecutorService executors) throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            //随机异常
            if (new Random().nextInt() % 2 >= 0) {
                int i = 12 / 0;
            }
            //pool-1-thread-n3
            logger.info("whenComplete run end ...");
        }, executors);
        //无论失败与否都会调用, 是执行当前任务的线程执行继续执行 whenComplete 的任务。
        future.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void t, Throwable action) {
                //pool-1-thread-n3 和执行线程共用一个线程
                logger.info("whenComplete 执行完成！");
            }

        });
        //无论失败与否都会调用, 是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行。
        future.whenCompleteAsync(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void t, Throwable action) {
                //pool-1-thread-n5 和执行线程共不共用一个线程
                logger.info("whenCompleteAsync 执行完成！");
            }

        }, executors);
        //失败了才会调用
        future.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable t) {
//                logger.error("whenComplete 执行失败！", t);
                logger.error("whenComplete 执行失败！" + t.getMessage());
                return null;
            }
        });
    }

    /**
     * Function<? super T,? extends U>
     * T：上一个任务返回结果的类型
     * U：当前任务的返回值类型
     * 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化
     *
     * @param executors
     * @throws Exception
     */
    private static void thenApply(ExecutorService executors) throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            //第一个任务
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
                logger.info("thenApply result1={}", result);
                return result;
            }
        }, executors).thenApply(new Function<Long, Long>() {
            //第二个任务依赖第一个任务的结果。
            @Override
            public Long apply(Long t) {
                long result = t * 5;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                logger.info("thenApply result2={}", result);
                return result;//返回第二个线程的结果
                //return t;//返回第一个线程的结果
            }
        });
        Long now = future.getNow(-1l);
        long result = future.get();
        logger.info("thenApply result={} ", result);
        logger.info("thenApply now={} ", now);
    }

    /**
     * handle 是执行任务完成时对结果的处理。
     * handle 方法和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。
     * thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法。
     *
     * @param executors
     * @throws Exception
     */
    public static void handle(ExecutorService executors) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                //随机异常
                if (new Random().nextInt() % 2 >= 0) {
                    int i = 12 / 0;
                }
                Integer result = new Random().nextInt(100);
                logger.info("handle result innner={}", result);
                return result;
            }
        }, executors).handle(new BiFunction<Integer, Throwable, Integer>() {
            @Override
            public Integer apply(Integer param, Throwable throwable) {
                int result = -1;
                if (throwable == null) {
                    result = param * 2;
                    logger.info("handle result2={}", result);
                } else {
                    logger.info("handle error={}", throwable.getMessage());
                }
                return result;//返回第二个线程的结果
//                return param;//返回第一个线程的结果
            }
        });
        logger.info("handle result={}", future.get());
    }

    /**
     * 接收任务的处理结果，并消费处理，无返回结果。
     *
     * @param executors
     * @throws Exception
     */
    public static void thenAccept(ExecutorService executors) throws Exception {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                logger.info("future-thenAccept-get"); //pool-1-thread-n2
                return new Random().nextInt(10);
            }
        }, executors).thenAccept(integer -> {
            logger.info("future-thenAccept-value={}", integer); //main
        });
        future.get();
        logger.info("thenAccept-future-get阻塞结束");

        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                logger.info("future2-thenAccept-get"); //pool-1-thread-n4
                return new Random().nextInt(10);
            }
        }, executors).thenAcceptAsync(integer -> {
            //pool-1-thread-n3
            logger.info("future2-thenAcceptAsync-value={}", integer);
        }, executors);
        future2.get();
        logger.info("thenAccept-future2-get阻塞结束");//main

        CompletableFuture<Void> future3 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                logger.info("future3-thenAccept-get");//pool-1-thread-5
                return new Random().nextInt(10);
            }
        }, executors).thenAcceptAsync(integer -> {
            logger.info("future3-thenAcceptAsync-value={}", integer);//ForkJoinPool.commonPool-worker-1
        });
        future3.get();
        logger.info("thenAccept-future3-get阻塞结束");//main
    }

    /**
     * 跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenAccept 。
     *
     * @param executors
     * @throws Exception
     */
    public static void thenRun(ExecutorService executors) throws Exception {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                return new Random().nextInt(10);
            }
        }).thenRun(() -> {
            logger.info("thenRun ...");//ForkJoinPool.commonPool-worker-1
        });
        future.get();
        logger.info("thenRun-future-get阻塞结束");//main

        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                return new Random().nextInt(10);
            }
        }).thenRunAsync(() -> {
            logger.info("future2 thenRunAsync ...");//ForkJoinPool.commonPool-worker-1
        });
        future2.get();
        logger.info("thenRun-future2-get阻塞结束");//main

        CompletableFuture<Void> future3 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                return new Random().nextInt(10);
            }
        }).thenRunAsync(() -> {
            logger.info("future3 thenRunAsync ...");//pool-1-thread-n1
        }, executors);
        future3.get();
        logger.info("thenRun-future3-get阻塞结束");//main
    }

    /**
     * thenCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。
     *
     * @param executors
     * @throws Exception
     */
    private static void thenCombine(ExecutorService executors) throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello1";
            }
        }, executors);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello2";
            }
        }, executors);
        CompletableFuture<String> result1 = future1.thenCombine(future2, new BiFunction<String, String, String>() {
            @Override
            public String apply(String t, String u) {
                logger.info("thenCombine t={},u={}", t, u);//main
                return t + " " + u;
            }
        });
        logger.info("thenCombine result1={}", result1.get());

        CompletableFuture<String> result2 = future1.thenCombineAsync(future2, new BiFunction<String, String, String>() {
            @Override
            public String apply(String t, String u) {
                logger.info("thenCombineAsync t={},u={}", t, u);//pool-1-thread-n5
                return t + " " + u;
            }
        }, executors);
        logger.info("thenCombineAsync result2={}", result2.get());
    }

    /**
     * 当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
     *
     * @param executors
     * @throws Exception
     */
    private static void thenAcceptBoth(ExecutorService executors) throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("f1={}", t);
                return t;
            }
        }, executors);

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("f2={}", t);
                return t;
            }
        }, executors);
        f1.thenAcceptBoth(f2, new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer t, Integer u) {
                logger.info("thenAcceptBoth f1={};f2={}", t, u);//pool-1-thread-n1
            }
        });
        f1.thenAcceptBothAsync(f2, new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer t, Integer u) {
                logger.info("thenAcceptBothAsync f1={};f2={}", t, u); //pool-1-thread-n2
            }
        }, executors);
    }

    /**
     * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作。
     *
     * @param executors
     * @throws Exception
     */
    private static void applyToEither(ExecutorService executors) throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(300);
                try {
                    TimeUnit.MILLISECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("applyToEither f1={}", t);
                return t;
            }
        }, executors);

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(300);
                try {
                    TimeUnit.MILLISECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("applyToEither f2={}", t);
                return t;
            }
        }, executors);

        CompletableFuture<Integer> result = f1.applyToEither(f2, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                logger.info("applyToEither result={}", t);
                return t * 2;
            }
        });
        logger.info("applyToEither={}", result.get());
    }


    /**
     * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作。
     *
     * @param executors
     * @throws Exception
     */
    private static void acceptEither(ExecutorService executors) throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(300);
                try {
                    TimeUnit.MILLISECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("acceptEither f1={}", t);
                return t;
            }
        }, executors);

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(300);
                try {
                    TimeUnit.MILLISECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("acceptEither f2={}", t);
                return t;
            }
        }, executors);

        f1.acceptEither(f2, new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                logger.info("acceptEither result={}", t);
            }
        });
    }

    /**
     * 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
     *
     * @param executors
     * @throws Exception
     */
    private static void runAfterEither(ExecutorService executors) throws Exception {
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(300);
                try {
                    TimeUnit.MILLISECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("runAfterEither f1={}", t);
                return t;
            }
        }, executors);

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(300);
                try {
                    TimeUnit.MILLISECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("runAfterEither f2={}", t);
                return t;
            }
        }, executors);

        f1.runAfterEither(f2, new Runnable() {
            @Override
            public void run() {
                long end = System.currentTimeMillis();
                logger.info("runAfterEither 上面有一个已经完成了。{}", end - start);
            }
        });

        f1.runAfterEitherAsync(f2, new Runnable() {
            @Override
            public void run() {
                long end = System.currentTimeMillis();
                logger.info("runAfterEitherAsync 上面有一个已经完成了。{}", end - start);
            }
        }, executors);
    }

    /**
     * 两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
     *
     * @param executors
     * @throws Exception
     */
    private static void runAfterBoth(ExecutorService executors) throws Exception {
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(300);
                try {
                    TimeUnit.MILLISECONDS.sleep(t);
                } catch (InterruptedException e) {
                    logger.info("runAfterBoth f1", e);
                }
                long end = System.currentTimeMillis();
                logger.info("runAfterBoth f1={},time={}", t, end - start);
                return t;
            }
        }, executors);

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(300);
                try {
                    TimeUnit.MILLISECONDS.sleep(t);
                } catch (InterruptedException e) {
                    logger.info("runAfterBoth f2", e);
                }
                long end = System.currentTimeMillis();
                logger.info("runAfterBoth f2={},time={}", t, end - start);
                return t;
            }
        });

        f1.runAfterBothAsync(f2, new Runnable() {
            @Override
            public void run() {
                long end = System.currentTimeMillis();
                logger.info("runAfterBoth 上面两个任务都执行完成了。{}", end - start);
            }
        }, executors);
    }

    /**
     * thenCompose 方法允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作
     *
     * @param executors
     * @throws Exception
     */
    private static void thenCompose(ExecutorService executors) throws Exception {
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(300);
                logger.info("thenCompose t1={}", t);
                return t;
            }
        }, executors).thenCompose(new Function<Integer, CompletionStage<Integer>>() {
            @Override
            public CompletionStage<Integer> apply(Integer param) {
                return CompletableFuture.supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int t = param * 2;
                        logger.info("thenCompose t2={}", t);
                        return t;
                    }
                });
            }

        });
        logger.info("thenCompose result :{} ", f.get());
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executors = Executors.newFixedThreadPool(5);
//      runAsync方法不支持返回值。
        runAsync(executors);
//      supplyAsync可以支持返回值。
        supplyAsync(executors);
//      计算结果完成时的回调方法
        whenComplete(executors);
//      当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化。
        thenApply(executors);
//      在 handle 中可以根据任务是否有异常来进行做相应的后续处理操作。而 thenApply 方法，如果上个任务出现错误，则不会执行 thenApply 方法。
        handle(executors);
//      接收任务的处理结果，并消费处理，无返回结果。
        thenAccept(executors);
//      跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenAccept 。
        thenRun(executors);
//      thenCombine 合并任务
        thenCombine(executors);
//      当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
        thenAcceptBoth(executors);
//      applyToEither 方法,两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作
        applyToEither(executors);
//      两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作
        acceptEither(executors);
//      两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
        runAfterEither(executors);
//      两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
        runAfterBoth(executors);
//      thenCompose 方法允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作。
        thenCompose(executors);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        executors.shutdown();
    }
}