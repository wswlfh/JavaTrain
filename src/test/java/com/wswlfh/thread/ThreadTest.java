package com.wswlfh.thread;

import org.junit.Test;

//继承Thread创建线程
class myThread extends Thread {
    myThread() {
        super();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("I'm thread in " + i);
        }
    }
}

public class ThreadTest {
    public static void main(String[] args) {
        //线程方法测试
    }

    /**
     * <p>创建线程方法测试：
     * <p>一、继承Thread类，子类重写run()方法，实例化子类对象调用start方法
     * <p>二、创建新的类实现Runnable接口，重写run()方法调用实例化对象
     *
     * <p>ps：
     * <p>1.必须调用start方法才能创建多线程
     * <p>2.可以使用内部匿名类创建线程<p>
     */
    @Test
    public void creatThread() {
        myThread myThread = new myThread();

        // 线程创建
        //事实证明交替进行
        myThread.start(); //使得线程自动调用run方法
        for (int i = 0; i < 10000; i++) {
            System.out.println("************* I'm main in " + i + " *************");
        }

        new Thread() {
            @Override
            public void run() {
                //TODO...
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO...
            }
        }).start();
    }

    /**
     * <p> 测试Thread类常用方法
     * <p> void start(): 启动线程，并执行对象的run()方法
     * <p> run(): 线程在被调度时执行的操作
     * <p> String getName(): 返回线程的名称
     * <p> void setName(String name):设置该线程名称
     * <p> static Thread currentThread(): 返回当前线程。在Thread子类中就 是this，通常用于主线程和Runnable实现类
     * <p> static void yield()：线程让步。暂停当前正在执行的线程，把执行机会让给优先级相同或更高的线程；若队列中没有同优先级的线程，忽略此方法
     * <p> join():当某个程序执行流中调用其他线程的 join() 方法时，调用线程将 被阻塞，直到 join() 方法加入的 join 线程执行完为止；低优先级的线程也可以获得执行
     * <p> static void sleep(long millis)：(指定时间:毫秒)。令当前活动线程在指定时间段内放弃对CPU控制,使其他线程有机会被执行,时间到后重排队；抛出InterruptedException异常
     */
    @Test
    public void threadMethodTest() {
        Thread currentThread = new Thread().currentThread();
        currentThread.setName("主线程");
        System.out.println(currentThread.getName());

        Thread newThread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    if (i % 2 == 0)
                        System.out.println(this.getName() + " " + i);
                    if (i % 10 == 0) {
                        try {
                            sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        newThread.setName("新线程");
        newThread.start();

        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 1)
                System.out.println(currentThread.getName() + " " + i);

            if (i == 997) {
                try {
                    newThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * <p>测试线程的优先级
     * <p>一、线程的优先等级
     * <p>  MAX_PRIORITY：10
     * <p>  MIN _PRIORITY：1
     * <p>  NORM_PRIORITY：5
     * <p>涉及的方法
     * <p>  getPriority() ：返回线程优先值
     * <p>  setPriority(int newPriority) ：改变线程的优先级
     * <p>说明
     * <p>  线程创建时继承父线程的优先级
     * <p>  低优先级只是获得调度的概率低，并非一定是在高优先级线程之后才被调用
     */
    @Test
    public void threadPriorityTest() throws InterruptedException {
        final int[] count = {10000};
        boolean[] start = {true};
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                this.setName("thread1");
                int points = 0;
                while (count[0] != 0) {
                    if (start[0]) {
                        points++;
                        count[0]--;
                        System.out.println(this.getName() + " get " + points);
                    }
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                this.setName("thread2");
                int points = 0;
                while (count[0] != 0) {
                    if (start[0]) {
                        points++;
                        count[0]--;
                        System.out.println(this.getName() + " get " + points);
                    }
                }
            }
        };

        thread1.setPriority(Thread.MIN_PRIORITY );
        thread2.setPriority(Thread.MAX_PRIORITY );

        thread1.start();
        thread2.start();


        Thread.sleep(3000);


    }


}


