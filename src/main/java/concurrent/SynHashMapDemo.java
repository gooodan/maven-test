package concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynHashMapDemo {
    public static void main(String[] args) {
        Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());

        method(null);
    }
    public static void method(String param) { switch (param) {
// 肯定不是进入这里
        case "sth": System.out.println("it's sth"); break;
// 也不是进入这里
        case "null": System.out.println("it's null"); break;
// 也不是进入这里
        default: System.out.println("default");
    } }

}
