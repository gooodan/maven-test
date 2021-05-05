package concurrent;

import java.util.concurrent.locks.ReentrantLock;


/**
 * 该例子主要演示了AQS的同步队列机制，
 */
public class LockTemplate {

    private static ReentrantLock lock = new ReentrantLock(true);


    private void modifyResource(String threadName) {
        int count = 0;
        System.out.println("线程：" + threadName + "准备获取锁");
        lock.lock();
        System.out.println("线程：" + threadName + "第一次加锁");
        count++;
        System.out.println("线程：" + threadName + "打了第" + count + "桶水");
        lock.lock();
        System.out.println("线程：" + threadName + "第二次加锁");
        count++;
        System.out.println("线程：" + threadName + "打了第" + count + "桶水");

        lock.unlock();
        System.out.println("线程：" + threadName + "释放一次锁");
        lock.unlock();
        System.out.println("线程：" + threadName + "释放两次锁");
    }


    public static void main(String[] args) {
        LockTemplate lt = new LockTemplate();

        new Thread(() -> {
            lt.modifyResource(Thread.currentThread().getName());
        }, "一号").start();

        new Thread(() -> {
            lt.modifyResource(Thread.currentThread().getName());
        }, "二号").start();

        new Thread(() -> {
            lt.modifyResource(Thread.currentThread().getName());
        }, "三号").start();
    }


}
