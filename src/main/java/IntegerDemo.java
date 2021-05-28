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
    }
}
