package cn.hust.highconcurrent.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-29 15:26
 **/
@Slf4j
public class CacheedThreadPoolTest {

    private static final ExecutorService service = Executors.newCachedThreadPool();


    public static void main(String[] args){

        for(int i = 0; i<20;i++){
            final int count = i;
            service.execute(()->
            {
                try {
                    Thread.sleep(2000);
                    log.info("{},{}",Thread.currentThread().getName(),count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }

        service.shutdown();

    }
}
