package cn.hust.highconcurrent.cache;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-30 14:12
 **/
@Slf4j
public class GuavaCacheTest2 {



    public static void main(String[] args) throws ExecutionException, InterruptedException {

       Cache<String,Integer> cache = CacheBuilder.newBuilder()
                //设置并发级别为3，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(3)
                //设置缓存最大容量为10，超过10之后就会按照LRU最近虽少使用算法来移除缓存项
                .maximumSize(10)
                //设置写缓存后8秒钟过期
                .expireAfterWrite(8, TimeUnit.SECONDS)
                //设置缓存容器的初始容量为5
                .initialCapacity(5)
                //设置要统计缓存的命中率
                .recordStats()
                //设置缓存的移除通知
                .removalListener(e -> {
                    log.info("{}被移除{}",e.getKey(),e.getValue());
                })

                .build();
           log.info("{}",cache.getIfPresent("key1"));
           for(int i = 0 ;i <9; i++){
               Thread.sleep(5);
               log.info("{}",cache.get("key1", new Callable<Integer>() {
                   @Override
                   public Integer call() throws Exception {
                       return -1;
                   }
               }));
               log.info("{}",cache.getIfPresent("key1"));
           }

           log.info("{}",cache.stats().toString());


    }


}
