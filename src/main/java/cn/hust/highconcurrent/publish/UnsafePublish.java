package cn.hust.highconcurrent.publish;

import cn.hust.highconcurrent.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-25 08:44
 **/
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] arrays = {"a","d","s"};


    //没有提供其更新的方法，却在Public在其发布出去
    public String[] getArrays(){
        return arrays;
    }


    public static void main(String[] args){

        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("arrays{}", Arrays.toString(unsafePublish.arrays));
        unsafePublish.getArrays()[0] = "d";
        log.info("arrays{}", Arrays.toString(unsafePublish.arrays));

    }
}
