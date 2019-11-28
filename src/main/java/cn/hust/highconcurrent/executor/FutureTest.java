package cn.hust.highconcurrent.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-28 19:52
 **/
@Slf4j
public class FutureTest {


    static class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            Thread.sleep(100);
            return "finish";
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        log.info("{}","等待消息");
       log.info("{}",future.get());



    }

}
