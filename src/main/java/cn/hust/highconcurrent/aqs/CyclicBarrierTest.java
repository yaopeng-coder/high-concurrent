package cn.hust.highconcurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-26 20:36
 **/
@Slf4j
public class CyclicBarrierTest {


    private static final Integer THREAD_TOTAL = 5;
    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {


        ExecutorService service = Executors.newCachedThreadPool();

        for(int i = 0; i<THREAD_TOTAL;i++){
            final int count = i;
            service.execute(()->{

                    try {
                        run(count);
                    } catch (Exception e) {
                       log.info("异常",e);
                    }

            });
        }

        service.shutdown();


    }


    public static void run(int i) throws InterruptedException, BrokenBarrierException, TimeoutException {
        Thread.sleep(100);
        log.info("准备就绪，{}",i);
        barrier.await(1, TimeUnit.MILLISECONDS);
        log.info("完成{}",i);


    }
}
