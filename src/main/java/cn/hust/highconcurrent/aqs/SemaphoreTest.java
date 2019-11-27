package cn.hust.highconcurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-26 20:36
 **/
@Slf4j
public class SemaphoreTest {


    private static final Integer THREAD_TOTAL = 20;

    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore  = new Semaphore(2);
        ExecutorService service = Executors.newCachedThreadPool();

        for(int i = 0; i<THREAD_TOTAL;i++){
            final int count = i;
            service.execute(()->{
                try {
                    semaphore.acquire();
                    run(count);
                    semaphore.release(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        service.shutdown();


    }


    public static void run(int i) throws InterruptedException {
        Thread.sleep(10);
        log.info("{}",i);
    }
}
