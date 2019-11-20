package cn.hust.highconcurrent.atomic;

import cn.hust.highconcurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-20 19:16
 **/
@Slf4j
@ThreadSafe
public class AtomicBooleanTest {

    private static  AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(50);
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for(int i = 0 ; i<1000;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    if( atomicBoolean.compareAndSet(false,true)){
                        log.info("atomicBoolean,{}",atomicBoolean.get());
                    }

                    semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });



        }
        countDownLatch.await();
        executorService.shutdown();




    }
}
