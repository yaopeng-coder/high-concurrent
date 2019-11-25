package cn.hust.highconcurrent.singleton;

import cn.hust.highconcurrent.annotations.ThreadSafe;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-25 10:36
 **/
@ThreadSafe
public class LazySingleton {

    private static LazySingleton singleton = null;

    private LazySingleton(){

    }

    public synchronized static LazySingleton getSingleton(){
        if(singleton == null){
            singleton = new LazySingleton();
        }

        return singleton;

    }
}
