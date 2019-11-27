package cn.hust.highconcurrent.immutable;

import cn.hust.highconcurrent.annotations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-25 12:12
 **/
@Slf4j
@NotThreadSafe
public class ImmutableObject {

    private static final String s1 = "hello";
    private static final Integer integer = 3;
    private static final Map<Integer,String> map = Maps.newHashMap();

    static{
        map.put(1,"hello");
        map.put(2,"xiaoya");
    }


    public static void main(String[] args){

       // s1 = "dd";
      //  integer = 3;
        map.put(3,"woaini");
        log.info("{}",map);



    }
}
