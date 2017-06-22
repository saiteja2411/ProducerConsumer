package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ProducerConsumer {

    public static void main(String[] args) throws InterruptedException {

        //NewClass nc= new NewClass();
        Shared s = new Shared();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    s.producer();
                } catch (Exception e) {
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    s.consumer();
                } catch (Exception e) {

                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }

    static void test() {
        System.out.println("static method test");
    }
}

class Shared {

    boolean flag = true;
    Queue<Integer> al;
    Scanner scr = new Scanner(System.in);
    int sizee = 2;
    int x = 0;
    final Object lock = new Object();

    Shared() {
        this.al = new LinkedList<>();
    }

    void producer() throws InterruptedException {
        //System.out.println("producerconsumer.Shared.producer()");

        synchronized (lock) {
            while (flag) {
                if (al.size() == sizee) {
                    lock.notify();
                    lock.wait();
                }
                System.out.println("producer produces " + x);
                al.add(x++);
                Thread.sleep(1000);
            }
        }
    }

    class inner {

        public int i = 0;
    }

    void consumer() throws InterruptedException {

        Thread.sleep(1000);
        synchronized (lock) {
            while (flag) {
                if (al.isEmpty()) {
                    lock.notify();
                    lock.wait();
                }
                System.out.println("consumer consumed " + al.remove());
                Thread.sleep(1000);

            }
        }
    }
}
