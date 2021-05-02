package com.company;

public class Matrix implements Addable {
    public double numbers[][];
    public int m;//number of rows of the matrix
    public int n;// number of columns of the matrix

    public Matrix(int m, int n) {
        this.m = m;
        this.n = n;
        this.numbers = new double[m][n];
    }




    public boolean setNumbers(double[] numbers1) {
        if (numbers1.length !=m * n || numbers1.length % n != 0)
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
        double tempNumbers[][] = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tempNumbers[i][j] = numbers[i][j];
            }
        }
        numbers = (double[][]) resizeArray(numbers, ntemp);
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (double[]) resizeArray(numbers[i], mTemp);
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
    public Matrix multiply(Matrix B){
        
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
}