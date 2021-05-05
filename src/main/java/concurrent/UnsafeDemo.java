package concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
        long offSet = unsafe.objectFieldOffset(Student.class.getDeclaredField("name"));
        Student s = new Student();
        System.out.println("before: " + s.name);
        unsafe.compareAndSwapObject(s, offSet, "init", "change");
//        unsafe.compareAndSwapObject(s, offSet, "init123", "change");
        System.out.println("after: " + s.name);
    }

    static class UnsafeInstance {
        public static Unsafe reflectGetUnsafe() throws NoSuchFieldException, IllegalAccessException {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        }
    }

    static class Student {
        private String name = "init";
        private int age;
    }
}
