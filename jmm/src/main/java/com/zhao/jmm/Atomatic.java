package com.zhao.jmm;

public class Atomatic {
   static int balance =1000;
   public  static  void add(){
       balance+=500;
   }
   public static void  devide(){
       balance-=500;
   }
    public static void main(String[] args) throws InterruptedException {
        Thread t1 =new Thread(()->{
            Atomatic.add();
        });
        t1.start();


        Thread t2 =new Thread(()->{
            Atomatic.devide();
        });
        t2.start();



        System.out.println("balance "+balance);
    }
}
