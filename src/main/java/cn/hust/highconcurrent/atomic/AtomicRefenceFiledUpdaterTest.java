package cn.hust.highconcurrent.atomic;

import cn.hust.highconcurrent.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-20 18:38
 **/
@Slf4j
@ThreadSafe
public class AtomicRefenceFiledUpdaterTest {


    @Getter
    private volatile Integer count = 0;

    private static AtomicReferenceFieldUpdater referenceFieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(AtomicRefenceFiledUpdaterTest.class,Integer.class,"count");

    public static void main(String[] args){
        AtomicRefenceFiledUpdaterTest test = new AtomicRefenceFiledUpdaterTest();
        referenceFieldUpdater.compareAndSet(test,0,5);
        referenceFieldUpdater.compareAndSet(test,3,6);
        referenceFieldUpdater.compareAndSet(test,5,8);
        log.info("count:{}",test.getCount());


    }




}
