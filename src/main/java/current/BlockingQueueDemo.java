package current;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

//        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        // 单一阻塞队列
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+"\t put 1");
                    blockingQueue.put("1");

                    System.out.println(Thread.currentThread().getName()+"\t put 2");
                    blockingQueue.put("2");

                    System.out.println(Thread.currentThread().getName()+"\t put 3");
                    blockingQueue.put("3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "AAA").start();

        new Thread(() -> {
            try {
                // 取一次
                try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println(Thread.currentThread().getName() + "\t take " + blockingQueue.take());

                // 取两次
                try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println(Thread.currentThread().getName() + "\t take " + blockingQueue.take());

                // 取三次
                try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println(Thread.currentThread().getName() + "\t take " + blockingQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();



        /* 阻塞插入，阻塞取，使用场景较少
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("=============");
         blockingQueue.put("d");

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();*/

        /* 插入不进去为false 取不到为null
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        */

        /* 插入不进去和取不到都报异常
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.add("d"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        */


    }

}
