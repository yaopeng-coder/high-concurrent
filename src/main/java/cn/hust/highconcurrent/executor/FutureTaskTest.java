package cn.hust.highconcurrent.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-28 19:56
 **/
@Slf4j
public class FutureTaskTest {

    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(100);
            return "finish";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask task = new FutureTask(new MyCallable());
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(task);
        log.info("{}","等待消息");
        log.info("{}",task.get());



    }



}
