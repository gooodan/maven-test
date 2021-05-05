package current;

import java.util.concurrent.locks.LockSupport;

public class InterupteDemo {
    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        LockSupport.park();
    }
}
