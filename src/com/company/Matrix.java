package com.company;

import java.util.Random;

public class Matrix implements Addable {
    public int[][] numbers;
    public int m;//number of rows of the matrix
    public int n;// number of columns of the matrix

    public Matrix(int m, int n) {
        this.m = m;
        this.n = n;
        this.numbers = new int[m][n];
    }


    static final public class MultiplicationException extends Exception{
        public String errorMessage;
        MultiplicationException(String str)
        {
            errorMessage=str;
        }
        public void message()
        {
            System.out.println(errorMessage);
        }
    }

    public boolean setNumbers(int[] numbers1) {
        if (numbers1.length <m * n )//|| numbers1.length % n != 0)
            return false;
        else {
            int x = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    numbers[i][j] = numbers1[x++];
                }
            }
            return true;
        }
    }

    public void print() {
        System.out.println("The Matrix is : ");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(numbers[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("**************");
    }

    public Matrix multiply(Matrix B,String str1){
        try {
            if(n==B.m)
            {
                Matrix result=new Matrix(m,B.n);
                for(int i=0;i<m;i++)
                {
                    for(int j=0;j<B.n;j++)
                    {
                        for(int z=0;z<n;z++)
                            result.numbers[i][j]+=numbers[i][z]*B.numbers[z][j];
                    }
                }
                return result;

            }
            else
                throw new MultiplicationException(str1);
        }
        catch (MultiplicationException x) {
            //x=new MultiplicationException(str1);
            x.message();
            return null;

        }

    }
    public static void main(String[] args) {
        Matrix m1=new Matrix(3,4);
        Matrix m2=new Matrix(4,2);
        Matrix m3=new Matrix(2,5);
        int[] arr={1,2,3,4,5,6,7,8,9,10,11,12};
        m1.setNumbers(arr);
        m2.setNumbers(arr);
        m3.setNumbers(arr);
        double start = System.currentTimeMillis();
        String str="“Exception occured\n" + "while trying to multiply two matrices of dimensions ("+m1.m+","+m1.n+") and ("+m2.m+","+m2.n+")";
        try {
            Matrix res = m1.multiply(m2, str);
            double current=System.currentTimeMillis()-start;
            res.print();
            System.out.println("Time Elapsed in millies in Multiplying m1 and m2: "+current);
        }
        catch(Exception ex)
        {

        }



        String str2="Exception occured\n" + "while trying to multiply two matrices of dimensions ("+m1.m+","+m1.n+") and ("+m3.m+","+m3.n+")";
        try {
            Matrix res2 = m1.multiply(m3, str2);
        }
        catch(Exception ex)
        {

        }
        Matrix m4=new Matrix(500,500);
        Matrix m5=new Matrix (500,500);
        String str3="Exception occured\n" + "while trying to multiply two matrices of dimensions ("+m4.m+","+m4.n+") and ("+m5.m+","+m5.n+")";
        int[] arr2=new int[250000];
        Random rd = new Random();
        for(int i=0;i<250000;i++)
            arr2[i] = rd.nextInt();
        boolean flag=m4.setNumbers(arr2);
        //System.out.println(flag);
        m5.setNumbers(arr2);
        start = System.currentTimeMillis();
        try {
            Matrix re3=m4.multiply(m5,str3);
            double current=System.currentTimeMillis()-start;
            //re3.print();
            System.out.println("Time Elapsed in millies in Multiplying m4 and m5: "+current);
        }catch(Exception e){}
    }
    // this function has been taken from https://www.source-code.biz/snippets/java/3.htm
    private static Object resizeArray(Object oldArray, int newSize) {
        int oldSize = java.lang.reflect.Array.getLength(oldArray);
        Class elementType = oldArray.getClass().getComponentType();
        Object newArray = java.lang.reflect.Array.newInstance(
                elementType, newSize);
        int preserveLength = Math.min(oldSize, newSize);
        if (preserveLength > 0)
            System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
        return newArray;
    }


    public void transpose() {
        int mTemp = m;
        int ntemp = n;
        int tempNumbers[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tempNumbers[i][j] = numbers[i][j];
            }
        }
        numbers = (int[][]) resizeArray(numbers, ntemp);
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int[]) resizeArray(numbers[i], mTemp);
        }
        m = ntemp;
        n = mTemp;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                numbers[i][j] = tempNumbers[j][i];
            }
    }

    @Override
    public Object add(Object obj1) {
        if(obj1 instanceof Matrix) {
            if (m != ((Matrix) obj1).m || n != ((Matrix) obj1).n)
                return null;
            Matrix temp = new Matrix(m, n);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    temp.numbers[i][j] = numbers[i][j] + ((Matrix) obj1).numbers[i][j];
                }
            }
            return temp;

        }
        else
            return null;
    }


}