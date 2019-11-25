package cn.hust.highconcurrent.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-25 18:56
 **/
@RestController
@RequestMapping("/threadlocal")
@Slf4j
public class HttpControllerTest {


    @RequestMapping("/test1")
    public String test1(){
        Long threadId = RequestHolder.get();
        log.info("test1,{}",threadId);

        return String.valueOf(threadId);

    }

    @RequestMapping("/test2")
    public String test2(){
        Long threadId = RequestHolder.get();
        log.info("test2,{}",threadId);

        return String.valueOf(threadId);

    }
}
