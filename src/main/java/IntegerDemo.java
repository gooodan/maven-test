import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IntegerDemo {
    public static void main(String[] args) {
//        Integer i = new Integer(100);
//        Integer j = new Integer(100);
//        System.out.println(i == j);
//
//        Integer x = new Integer(100);
//        Integer y = new Integer(300);
//        System.out.println(x < y);

        Integer a = Integer.valueOf(5);
        Integer b = Integer.valueOf(5);
        System.out.println(a == b);

        Integer x = Integer.valueOf(155);
        Integer y = Integer.valueOf(155);
        System.out.println(x == y);

        Byte i = 2;
        System.out.println(i);

        float f1 = 1.0f - 0.9f;
        float f2 = 0.9f - 0.8f;
        float diff = 1e-6f;
        System.out.println(f1 == f2);

        List<String> list = new ArrayList<String>() {{
            add("Hollis");
            add("hollis");
            add("HollisChuang");
            add("H");
        }};

        Iterator<String> iterator = list.iterator();
        list.clear();
        while (iterator.hasNext()) {
            String item = iterator.next();
            iterator.remove();
        }
        System.out.println(list.toString());

        /*
        报ConcurrentModificationException
        for (String str : list) {
            list.remove(str);
        }
        */



    }
}
