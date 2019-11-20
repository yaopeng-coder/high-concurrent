package cn.hust.highconcurrent.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-20 18:29
 **/
@Slf4j
public class AtomicRefenceTest {

    private static AtomicReference<People> reference = new AtomicReference<People>();

    public static void main(String[] args){

        People people1 = new People("小红",22);
        People people2 = new People("小刚",23);
        reference.set(people1);
        people1.setId(222);
        log.info("people{},reference{}",people1,reference.get());


        reference.compareAndSet(people1,people2);
        log.info("people2{},reference{}",people2,reference.get());


    }






}

@Data
@AllArgsConstructor
class People{
    String name;
    int id ;



  }
