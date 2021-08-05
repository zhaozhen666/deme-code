package com.zhao.jmm;

import java.util.Date;

public class JIT {

    static boolean stop =false;
    public static void main(String[] args) throws InterruptedException{

        new Thread(()->{
            try {
                Thread.sleep(1000);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            stop =true;
        }).start();

        System.out.println("start "+new Date());
        new Thread(()->{
            try {
                Thread.sleep(1000);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(" thread 2  stop "+stop);
        }).start();
        while (true){
            boolean b =stop;
            if (b){
                break;
            }
        }
        System.out.println("end b="+stop);
    }
}
