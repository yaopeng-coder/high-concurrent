package cn.hust.highconcurrent.threadlocal;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-25 18:35
 **/
public class RequestHolder {

    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<>();


    public static void set(Long id){
        THREAD_LOCAL.set(id);
    }

    public static Long get(){
        return THREAD_LOCAL.get();
    }

    public static void remove(){
        THREAD_LOCAL.remove();
    }



}
