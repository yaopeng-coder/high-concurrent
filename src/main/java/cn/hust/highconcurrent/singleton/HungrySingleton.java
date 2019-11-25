package cn.hust.highconcurrent.singleton;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-25 10:42
 **/
public class HungrySingleton {


    //注意 ，这里一定要先定义然后再在static中初始化，否则类初始化是按照static区域顺序进行初始化的，会导致这个单例被new出来后又被置为null
    private static HungrySingleton hungrySingleton = null;

    static {
        hungrySingleton = new HungrySingleton();
    }

    private HungrySingleton(){

    }

    public static HungrySingleton getHungrySingleton(){
        return hungrySingleton;
    }
}
