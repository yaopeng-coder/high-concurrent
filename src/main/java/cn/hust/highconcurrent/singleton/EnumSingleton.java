package cn.hust.highconcurrent.singleton;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-25 10:45
 **/
public class EnumSingleton {

    private EnumSingleton(){

    }

    public EnumSingleton getSingleton(){
        return Singleton.intance.getSingleton();
    }

    enum Singleton{
        //这里的instance也是一个单例，在其构造函数中又实现了一个单例
        intance;

       private EnumSingleton singleton = null;

        Singleton(){
            singleton = new EnumSingleton();
        }

        private EnumSingleton getSingleton(){
            return singleton;
        }
    }
}
