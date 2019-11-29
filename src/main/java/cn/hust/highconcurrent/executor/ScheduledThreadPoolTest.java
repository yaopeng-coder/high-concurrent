package cn.hust.highconcurrent.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-29 15:26
 **/
@Slf4j
public class ScheduledThreadPoolTest {

    private static final ScheduledExecutorService service = Executors.newScheduledThreadPool(3);


    public static void main(String[] args) throws InterruptedException {

        for(int i = 0; i<20;i++){
            final int count = i;
//            service.execute(()->
//            {
//                try {
//                    Thread.sleep(2000);
//                    log.info("{},{}",Thread.currentThread().getName(),count);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            });
            Thread.sleep(1000);
            service.schedule(()->{
                log.info("{},{}",Thread.currentThread().getName(),count);
            },2, TimeUnit.SECONDS);
        }

        service.shutdown();

    }
}
