package cn.hust.highconcurrent.singleton;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-25 10:37
 **/
public class LazySingletonDoubleCheck {


    private static volatile LazySingletonDoubleCheck singleton = null;

    private LazySingletonDoubleCheck(){

    }

    public  static LazySingletonDoubleCheck getSingleton(){
        if(singleton == null){
            synchronized (LazySingletonDoubleCheck.class){
                if(singleton == null){
                    singleton = new LazySingletonDoubleCheck();
                }
            }
        }

        return singleton;

    }
}
