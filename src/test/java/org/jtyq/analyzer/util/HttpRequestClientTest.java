package org.jtyq.analyzer.util;

import org.junit.Test;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class HttpRequestClientTest {

    @Test
   public void test() throws IOException {
//        HttpRequestClient httpRequestClient = new HttpRequestClient();
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer("0A6HE11bI8lI2hQ78sun6q==");
//        Socket socket = new Socket();
//        socket.connect();
//        socket.bind();
//        socket.setKeepAlive(true);
        final int count = 1000;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                    try {
                        cyclicBarrier.await();
                        long start = System.currentTimeMillis();
                        HttpRequestClient httpRequestClient = new HttpRequestClient();
                        String s = httpRequestClient.sendHttpGet("https://www.baidu.com",null,null);
                        long end = System.currentTimeMillis();
                        //System.out.println(s);
                        System.out.println((end-start)+"毫秒");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("all is finished" +(end -start));


    }

     String[] hosts1 = {
            "https://gab.122.gov.cn",
            "https://xz.122.gov.cn",
            "https://sc.122.gov.cn",
            "https://hb.122.gov.cn",
            "https://gs.122.gov.cn",
            "https://jx.122.gov.cn",
            "https://gx.122.gov.cn",
            "https://gz.122.gov.cn",
            "https://hl.122.gov.cn",
            "https://sh.122.gov.cn",
            "https://jl.122.gov.cn"};

    String[] hosts2 = {
           "https://xueqiu.com/v4/stock/quotec.json?code=SH600753",
            "https://xueqiu.com/v4/stock/quotec.json?code=SH600753",
            "https://xueqiu.com/v4/stock/quotec.json?code=SH600753"
    };

    @Test
    public void test1(){
        HttpRequestClient client = new HttpRequestClient();
        for (String host : hosts2) {
           String str =  client.sendHttpGet(host,null,null);
        }
    }
}