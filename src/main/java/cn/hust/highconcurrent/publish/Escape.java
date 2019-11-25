package cn.hust.highconcurrent.publish;

import cn.hust.highconcurrent.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-25 08:50
 **/
@Slf4j
@NotThreadSafe
public class Escape {

    private String  name = null;

    public Escape(){
        new InnerClass();
        name = "aaa";
    }


    class  InnerClass{

        InnerClass(){
            log.info("name{}",Escape.this.name);
        }

    }

    public static void main(String[] args){

        new Escape();


    }
}
