package cn.hust.highconcurrent.aqs;

import cn.hust.highconcurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-20 12:50
 **/
@Slf4j
@ThreadSafe
public class ReentrantLockTest {

    //请求总数目
    private static final int THREAD_TOTAL = 1000;

    //同时并发请求的数目
    private static final int THREAD_CONCURRENT = 20;

    private static int count = 0 ;

    private static Lock lock = new ReentrantLock();




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
                        log.error("发生异常,",e);
                    }
                    countDownLatch.countDown();
                }
            }

            );

        }

        //countdown变为0主线程才可继续运行
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count="+count);

    }


    public static void add(){
        try {
            if(lock.tryLock(1, TimeUnit.MILLISECONDS)){
                count ++;
                lock.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
