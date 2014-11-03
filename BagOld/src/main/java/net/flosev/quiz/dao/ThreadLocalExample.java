package net.flosev.quiz.dao;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalExample {
    public static MyTL<String> memory = new MyTL<>();
    public static void main(String[] args) throws InterruptedException {
        System.out.println("0:" + memory.get());
        memory.set("AAA");
        System.out.println("0:" + memory.get());

        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("1:" + memory.get());
                memory.set("BBB");
                System.out.println("1:" + memory.get());
            }
        });
        thread.start();

        thread.join();
        System.out.println("0:" + memory.get());
    }
}

class MyTL<T> {
    private Map<Thread, T> map = new HashMap<>();
//    private Map<Thread, T> map = new ConcurrentHashMap<>();
//    private Map<Thread, T> map = Collections.synchronizedMap(new HashMap<Thread, T>());
//    private Map<Thread, T> map = new Hashtable<>();
    public synchronized void set(T value) {
        map.put(Thread.currentThread(), value);
    }
    public synchronized T get() {
        return map.get(Thread.currentThread());
    }    
}



