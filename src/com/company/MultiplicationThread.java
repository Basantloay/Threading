package com.company;

public class MultiplicationThread implements Runnable {
    private Matrix A;
    private Matrix B;
    private Matrix result;
    MultiplicationThread(Matrix firstMatrix,Matrix secondMatrix){
        A=firstMatrix;
        B=secondMatrix;
        result =new Matrix(A.m,B.n);
    }
    @Override
    public void run() {
       if(Integer.parseInt(Thread.currentThread().getName())==1)
       {

       }
       else if(Integer.parseInt(Thread.currentThread().getName())==2)
       {

       }
    }
}
