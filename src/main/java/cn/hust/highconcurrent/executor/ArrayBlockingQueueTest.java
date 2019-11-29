package cn.hust.highconcurrent.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-29 10:38
 **/
@Slf4j
public class ArrayBlockingQueueTest {

    private final static BlockingQueue<User> blockingQueue = new ArrayBlockingQueue<>(1);


    public static void main(String[] args) {

        new Thread(new Producer(blockingQueue),"生产者一号").start();
        new Thread(new Producer(blockingQueue),"生产者二号").start();
        new Thread(new Consumer(blockingQueue), "消费者一号").start();
        new Thread(new Consumer(blockingQueue),"消费者二号").start();


    }


   static  class Producer implements Runnable {
        private final BlockingQueue<User> blockingQueue;

        Producer(BlockingQueue blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            while (true) {
                User user = new User("小红");
                try {
                    blockingQueue.put(user);
                    log.info("生产者{}添加商品{}", Thread.currentThread().getName(),user);
                } catch (InterruptedException e) {
                    log.info("生产者出异常{}", Thread.currentThread().getName());
                }
            }

        }
    }


   static class Consumer implements Runnable {
        private final BlockingQueue<User> blockingQueue;

        Consumer(BlockingQueue blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            while (true) {
                User user = new User("小明");
                try {
                    User take = blockingQueue.take();
                    log.info("消费者{}消费商品,{}", Thread.currentThread().getName(),take);
                } catch (InterruptedException e) {
                    log.info("消费者出异常{}", Thread.currentThread().getName());
                }
            }

        }
    }


    //队列存储的元素
   static  class User {
        private String name;

        User(String name) {
            this.name = name;
        }
    }

}
