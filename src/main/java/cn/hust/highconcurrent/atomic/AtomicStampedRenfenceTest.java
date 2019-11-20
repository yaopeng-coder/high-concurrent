package cn.hust.highconcurrent.atomic;

import cn.hust.highconcurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-20 18:54
 **/
@Slf4j
@ThreadSafe
public class AtomicStampedRenfenceTest {

    private static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<Integer>(0,0);

    static  int total = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(50);
        CountDownLatch countDownLatch = new CountDownLatch(1000);

        for(int i = 0 ; i<1000;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    Integer stamp = stampedReference.getStamp();
                    Integer value = stampedReference.getReference();
                    if(!(stampedReference.compareAndSet(value,value+1,stamp,stamp+1))){
                        total ++;
                        log.info("出错啦stamp{},value{}",stampedReference.getStamp(),stampedReference.getReference());
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
        log.info("成功啦stamp{},value{},total{}",stampedReference.getStamp(),stampedReference.getReference(),total);




    }

}
