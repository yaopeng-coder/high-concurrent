package cn.hust.highconcurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-28 16:03
 **/
@Slf4j
public class ReentReadWriteLockTest {

    private static Map<Integer,String> map = new HashMap<>();

    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    private static Lock readLock = ReentReadWriteLockTest.lock.readLock();

    private static Lock writeLock = ReentReadWriteLockTest.lock.writeLock();

    public static void main(String[] args){

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);


        new Thread(() -> {
            writeLock.lock();
            for(int i = 0; i<20;i++){
                map.put(i,String.valueOf(i));
                log.info("writelock1{}----{}",Thread.currentThread().getName(),i);
            }
            writeLock.unlock();


        }).start();
        new Thread(() -> {

            writeLock.lock();
            for(int i = 0; i<20;i++){
                map.put(i,String.valueOf(i*2));
                log.info("writelock2{}------{}",Thread.currentThread().getName(),i*2);
            }
            writeLock.unlock();


        }).start();
        new Thread(() -> {
            try {
                cyclicBarrier.await();
                readLock.lock();
                for(int i = 0; i<map.size();i++){
                    map.get(i);
                    Thread.sleep(10);
                    log.info("readlock1{}----{}",Thread.currentThread().getName(),map.get(i));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }finally {
                    readLock.unlock();
            }


        }).start();

        new Thread(() -> {
            try {
                cyclicBarrier.await();
                readLock.lock();
                for(int i = 0; i<map.size();i++){
                    map.get(i);
                    Thread.sleep(10);
                    log.info("readlock2{}------{}",Thread.currentThread().getName(),map.get(i));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            readLock.unlock();

        }).start();



    }




}
