package cn.hust.highconcurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-26 20:36
 **/
@Slf4j
public class CountdownLatchTest {


    private static final Integer THREAD_TOTAL = 200;

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(THREAD_TOTAL);
        ExecutorService service = Executors.newCachedThreadPool();

        for(int i = 0; i<200;i++){
            final int count = i;
            service.execute(()->{
                try {
                    run(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            });
        }

        latch.await(10, TimeUnit.MILLISECONDS);
        log.info("主线程结束");
        service.shutdown();


    }


    public static void run(int i) throws InterruptedException {
        Thread.sleep(10);
        log.info("{}",i);
    }
}
