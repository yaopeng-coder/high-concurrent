package cn.hust.highconcurrent.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2020-03-07 20:05
 **/
@Slf4j
public class SocketService implements Runnable {

    private Socket socket;

    public SocketService() {
    }

    public SocketService(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        log.info("SocketService 服务端任务开始执行");
        OutputStream outputStream = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            //接受消息
            socket.setSoTimeout(10000);// 10秒还没有得到数据，直接断开连接
            is = socket.getInputStream();
            isr = new InputStreamReader(is,"UTF-8");
            br = new BufferedReader(isr);
            StringBuffer sb = SocketClient.segmentRead(br);
            socket.shutdownInput();
            log.info("SocketService accept info is {}", sb.toString());

            //服务端处理 模拟服务端处理耗时
            Thread.sleep(2000);
            String response  = sb.toString();

            //返回处理结果给客户端
            outputStream = socket.getOutputStream();
            byte[] bytes = response.getBytes(Charset.forName("UTF-8"));
            SocketClient.segmentWrite(bytes, outputStream);
            socket.shutdownOutput();

            //关闭流
            SocketClient.close(socket,outputStream,isr,br,is);
            log.info("SocketService 服务端任务执行完成");
        } catch (IOException e) {
            log.error("SocketService IOException", e);
        } catch (Exception e) {
            log.error("SocketService Exception", e);
        } finally {
            try {
                SocketClient.close(socket,outputStream,isr,br,is);
            } catch (IOException e) {
                log.error("SocketService IOException", e);
            }
        }
    }
}
