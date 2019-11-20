package cn.hust.highconcurrent.atomic;

import cn.hust.highconcurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-20 12:50
 **/
@Slf4j
@ThreadSafe
public class AtomicLongTest {

    //请求总数目
    private static final int THREAD_TOTAL = 1000;

    //同时并发请求的数目
    private static final int THREAD_CONCURRENT = 20;

    private static AtomicLong count = new AtomicLong(0);




    public static void main(String[] args) throws InterruptedException {

        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //创建信号量，大小为10
        Semaphore semaphore = new Semaphore(THREAD_CONCURRENT);
        //用来阻塞线程，变为0才可继续执行
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_TOTAL);

        for(int i = 0 ; i < THREAD_TOTAL; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        add();
                        semaphore.release();
                    } catch (InterruptedException e) {
                        log.error("long操作信号量发生异常,",e);
                    }
                    countDownLatch.countDown();
                }
            }

            );

        }

        //countdown变为0主线程才可继续运行
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count="+count.get());

    }


    public static void add(){
        count.incrementAndGet();
    }


}