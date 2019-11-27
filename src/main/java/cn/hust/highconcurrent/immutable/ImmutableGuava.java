package cn.hust.highconcurrent.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-25 12:41
 **/
@Slf4j
public class ImmutableGuava {


    private  final ImmutableList list ;
    private final ImmutableSet set = ImmutableSet.of("ddd","aaaa");
    private final ImmutableMap map = ImmutableMap.builder().put(1,"sss").put(2,"ssss").build();

    ImmutableGuava(ImmutableList list1){
        this.list = list1;
    }

    public static void main(String[] args){
         ImmutableList list1 = ImmutableList.builder().add("a","b","c").build();
         ImmutableGuava immutableGuava = new ImmutableGuava(list1);
         log.info("{}",immutableGuava.list);

         list1 = ImmutableList.builder().add("a").build();
         log.info("{}",immutableGuava.list);



    }

}
