package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Print100 {
    static AtomicInteger num = new AtomicInteger(1);
    static ReentrantLock lock = new ReentrantLock();
    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) {
        doCountDownLatch();
    }


    private static void  doCountDownLatch(){
        Thread t1 = new Thread() {
            @Override public void run() {
                while (num.intValue() < 100) {
                    if (num.intValue() % 2 == 1) {

                        System.out.println("奇数线程:" + num.intValue());
                        num.incrementAndGet();
                    }
//                    countDownLatch.countDown();
                }
            }
        };

        Thread t2 = new Thread() {
            @Override public void run() {
                while (num.intValue() <= 100) {
                    if (num.intValue() % 2 == 0) {
                        System.out.println("偶数线程:" + num.intValue());
                        num.incrementAndGet();
                    }
//                    countDownLatch.countDown();
                }
            }
        };
        t1.start();
        t2.start();
        try {
//            countDownLatch.await();
        } catch (Throwable e) {
            System.out.println(e);
        }
    }
}
