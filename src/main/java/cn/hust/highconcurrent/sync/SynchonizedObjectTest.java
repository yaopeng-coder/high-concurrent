package cn.hust.highconcurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-21 10:23
 **/
@Slf4j
public class SynchonizedObjectTest {

    private  Object lock = new Object();


    //修饰普通方法
    public synchronized void test1(int j){
        for(int i = 0;i < 10; i++){
            log.info("j={},i={},",j,i);
        }

    }

    //修饰同步代码块
    public  void test2(int j){
        synchronized (lock){
            for(int i = 0;i < 10; i++){
                log.info("j={},i={},",j,i);
            }
        }
    }

    public static void main(String[] args){
        SynchonizedObjectTest testObject1 = new SynchonizedObjectTest();
        SynchonizedObjectTest testObject2 = new SynchonizedObjectTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(()->{
//            testObject1.test1(1);
//        });
//
//        executorService.execute(()->{
//            testObject1.test1(2);
//        });

        executorService.execute(()->{
            testObject1.test2(3);
        });

        executorService.execute(()->{
            testObject2.test2(4);
        });

        //一定要关闭线程池，否则线程仍在运行
        executorService.shutdown();



    }



}
