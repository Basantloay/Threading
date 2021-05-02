package com.company;

import java.util.Random;

public class MultiplicationThread implements Runnable {
    private Matrix A;
    private Matrix B;
    public Matrix result;
    MultiplicationThread(Matrix firstMatrix,Matrix secondMatrix){
        A=firstMatrix;
        B=secondMatrix;
        result =new Matrix(A.m,B.n);
    }
    @Override
    public void run() {
       if(Integer.parseInt(Thread.currentThread().getName())==1)
       {
           double count= Math.floor(A.m/2);
           for(int i=0;i<count;i++)
           {
               for(int j=0;j<B.n;j++)
               {
                   for(int z=0;z<B.m;z++)
                       result.numbers[i][j]+=A.numbers[i][z]*B.numbers[z][j];
               }
           }
           // result.print();
       }
       else if(Integer.parseInt(Thread.currentThread().getName())==2)
       {
           double count= Math.floor(A.m/2);
           for(int i=(int)count;i<A.m;i++)
           {
               for(int j=0;j<B.n;j++)
               {
                   for(int z=0;z<B.m;z++)
                       result.numbers[i][j]+=A.numbers[i][z]*B.numbers[z][j];
               }
           }
           //result.print();
       }

    }

    public static void main(String[] args) throws InterruptedException {
        Matrix m1 = new Matrix(3, 4);
        Matrix m2 = new Matrix(4, 2);
        int[] arr={1,2,3,4,5,6,7,8,9,10,11,12};
        m1.setNumbers(arr);
        m2.setNumbers(arr);

        Thread t1 = new Thread (new MultiplicationThread(m1,m2));
        Thread t2 = new Thread (new MultiplicationThread(m1,m2));
        t1.setName("1");
        t2.setName("2");
        double start = System.currentTimeMillis();
        t1.start(); t2.start();
        t1.join(); t2.join(); // to wait until two threads two finish multiplication process to start next command(calculate Elapsed time)
        double current=System.currentTimeMillis()-start;
        System.out.println("Time Elapsed in millies in Multiplying m1 and m2: "+current);
        Matrix m4=new Matrix(500,500);
        Matrix m5=new Matrix (500,500);
        int[] arr2=new int[250000];
        Random rd = new Random();
        for(int i=0;i<250000;i++)
            arr2[i] = rd.nextInt();
        m4.setNumbers(arr2);
        m5.setNumbers(arr2);
        Thread t3 = new Thread (new MultiplicationThread(m4,m5));
        Thread t4 = new Thread (new MultiplicationThread(m4,m5));
        t3.setName("1");
        t4.setName("2");
        start = System.currentTimeMillis();
        t3.start(); t4.start();
        t3.join(); t4.join(); // to wait until two threads two finish multiplication process to start next command(calculate Elapsed time)
        current=System.currentTimeMillis()-start;
        //re3.print();
        System.out.println("Time Elapsed in millies in Multiplying m4 and m5: "+current);

    }
}
