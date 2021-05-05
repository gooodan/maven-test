package current;

public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("线程："+Thread.currentThread().getName() + " 启动");
        Thread t1 = new Thread(() -> {
            System.out.println("线程："+Thread.currentThread().getName() + " 启动");
            try {
                System.out.println("线程："+Thread.currentThread().getName() + " 休息5秒");
                Thread.sleep(5000);
                System.out.println("线程："+Thread.currentThread().getName() + " 执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"一号");

        // start要放在join前，join才起作用
        t1.start();

        // 主线程让t1执行1秒再执行
//        t1.join(1000);
        // 主线程让t1执行完毕再执行
//        t1.join();

        System.out.println("线程："+Thread.currentThread().getName() + " 执行完毕");
    }
}
