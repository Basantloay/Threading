package com.company;
import java.util.Random;

public class Main {

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
		re3.print();
		System.out.println("Time Elapsed in millies in Multiplying m4 and m5: "+current);
	}catch(Exception e){}
	}
}
