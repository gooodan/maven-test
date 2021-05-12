package concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                getResource();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程一").start();

        new Thread(() -> {
            try {
                getResource();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程二").start();
    }

    public static void getResource() throws InterruptedException {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " 拿到资源");
        TimeUnit.SECONDS.sleep(5);
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + " 释放资源");

    }
}
