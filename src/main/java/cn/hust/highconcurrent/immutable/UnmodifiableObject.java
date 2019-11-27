package cn.hust.highconcurrent.immutable;

import cn.hust.highconcurrent.annotations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-25 12:12
 **/
@Slf4j
@NotThreadSafe
public class UnmodifiableObject {



    private static  final Map<Integer,String> map ;

    private final  Map<Integer,String> map1;

    UnmodifiableObject(Map<Integer,String> newMap){
       // this.map1 = Collections.unmodifiableMap(newMap);
        this.map1 = Collections.unmodifiableMap(Maps.newHashMap(newMap));

    }



    static{
        map = Maps.newHashMap();
        map.put(1,"hello");
        map.put(2,"xiaoya");

    }




    public static void main(String[] args){

       // s1 = "dd";
      //  integer = 3;
        //下面Map能通过
//        map.put(3,"woaini");
//        log.info("{}",map);


        Map<Integer,String> newmap = Maps.newHashMap();
        newmap.put(1,"ee");
        UnmodifiableObject object = new UnmodifiableObject(newmap);
        log.info("{}",object.map1);

        //加了Collections.unmodifiableMap后，自己putobject.map1.put(2,"aa")会抛异常
//        object.map1.put(2,"aa");
//        log.info("{}",object.map1);

        //但外部这样不抛异常
        newmap.put(3,"qqq");
        log.info("{}",object.map1);





    }
}
