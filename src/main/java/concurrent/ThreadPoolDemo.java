package concurrent;

import java.util.concurrent.*;

public class ThreadPoolDemo {

    private static final ExecutorService executorService =
            new ThreadPoolExecutor(2, 5, 100L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3));

    public static void main(String[] args) {

        executor8();
        try {TimeUnit.SECONDS.sleep(15);} catch (InterruptedException e) {e.printStackTrace();}
        executor10();
    }

    private static void executor8() {
        try {
            for (int i = 1; i <= 6; i++) {
                final int num = i;
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"号窗口，服务顾客"+num);
                    try {TimeUnit.SECONDS.sleep(4);} catch (InterruptedException e) {e.printStackTrace();}
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        System.out.println("executor8执行完成");
    }

    private static void executor10() {

        try {
            for (int i = 1; i <= 6; i++) {
                final int num = i;
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"号窗口，服务顾客"+num);
                    try {TimeUnit.SECONDS.sleep(4);} catch (InterruptedException e) {e.printStackTrace();}
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        System.out.println("executor10执行完成");
    }

}
