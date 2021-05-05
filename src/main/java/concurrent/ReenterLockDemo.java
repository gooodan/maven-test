package concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockDemo {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void test() {
        lock.lock();
        System.out.println("锁住了");
        lock.unlock();
        System.out.println("解锁了");
    }

    public static void main(String[] args) {
        test();
    }

}

class Phone {


    public synchronized void sendSMS() {

    }
}
