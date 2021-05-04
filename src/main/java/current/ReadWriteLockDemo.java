package current;

import java.util.HashMap;
import java.util.Map;

public class ReadWriteLockDemo {

    private volatile Map<String, Object> map = new HashMap<>();

    public void put() {
        System.out.println(Thread.currentThread());
    }

    public static void main(String[] args) {

    }

}
