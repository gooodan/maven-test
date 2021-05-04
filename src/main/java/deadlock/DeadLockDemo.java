package deadlock;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {

    private static final String lock1 = "aaa";
    private static final String lock2 = "bbb";

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName()+"\t 获得锁"+lock1 +", 尝试获得锁："+lock2);
                try { TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "\t 获得锁lock1和lock2");
                }
            }
        }, "AAA").start();

        new Thread(() -> {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName()+"\t 获得锁"+lock2 +", 尝试获得锁："+lock1);
                try { TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "\t 获得锁lock1和lock2");
                }
            }
        }, "BBB").start();
    }
}
