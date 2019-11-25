package cn.hust.highconcurrent.singleton;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-25 10:39
 **/
public class InnerClassSingleton {

   static class InnerClass{
       private static InnerClassSingleton singleton = new InnerClassSingleton();
   }

    private InnerClassSingleton(){

    }

    public  static InnerClassSingleton getSingleton(){


        return InnerClass.singleton ;

    }
}
