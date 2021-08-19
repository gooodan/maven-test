import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class TimeDemo {

    static ExecutorService threadPool = new ThreadPoolExecutor(5, 5,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(5));

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");


        for (String str : list) {
            Future<?> future = threadPool.submit(() -> {
                System.out.println("执行到：" + str);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ("3".equals(str)) {
                    System.out.println("到3了，休息10s");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            try {
                future.get(14,TimeUnit.SECONDS);
            } catch (Exception e) {
                System.out.println("跳过3");
            }
        }
        threadPool.shutdown();
    }
}
