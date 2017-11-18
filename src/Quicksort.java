/*Ryland Atkins
 * 
 * This is entirely my own work.
 */

import java.io.*;
import java.util.*;

public class Quicksort
{
	protected static int partition(int A[])
	{		
		int l = 0;
		int r =	A.length-1;
		
		medianOf3(l,r,A);
		
		int p = A[l];
		
		int i = 1;
		int j = A.length-1;
		
		while(i < j)
		{
			while(i < A.length-1 && A[i] < p){
				i++;}
			
			while(A[j] > p){
				j--;}

			swap(i, j, A);
		}
		swap(i, j, A);
		
		if(A[j] < p){
			swap(0, j, A);}
		
		return j;
	}
	
	public static void quicksort(int A[])
	{
		int l = 0;
		int r = A.length-1;
		int s = 0;
		
		if(l<r)
		{
			s = partition(A);
			int B[] = Arrays.copyOfRange(A, 0, s);
			int C[] = Arrays.copyOfRange(A, s+1, A.length);
			
			if(B.length < 16)
				inSort(B);
			else
				quicksort(B);

			if(C.length < 16)
				inSort(C);
			else
				quicksort(C);

			for(int i = l;i < s;i++){
				A[i] = B[i];}
			
			int k = 0;
			for(int j = s+1;j <= r;j++)
			{
				A[j] = C[k];
				k++;
			}
		}
	}
	
	public static void inSort(int A[])
	{
		int j = 0;
		for(int i = 1;i < A.length;i++)
		{
			j = i;
			while(j > 0 && A[j-1] > A[j])
			{
				swap(j-1,j,A);
				j--;
			}
		}
	}
	
	public static void medianOf3(int l,int r,int A[])
	{
		int m = (l+r)/2;
		
		if (A[l] > A[m]){
			swap(l, m, A);}
		if (A[l] > A[r]){
			swap(l, r, A);}
		if (A[m] > A[r]){
		    swap(m, r, A);}

		swap(m, l, A);
		
		return;
	}
	
	public static void swap(int a,int b,int A[])
	{
		int temp = A[a];
		A[a] = A[b];
		A[b] = temp;
		
		return;
	}
	
	public static int[] readArray(String filename)
	{	
		try
		{
			File f = new File(filename);
			Scanner sc = new Scanner(f);
			
			int size = sc.nextInt();
			int A[] = new int[size];
			
			for(int i = 0; i < size; i++)
			{
				A[i] = sc.nextInt();
			}
			sc.close();
			return A;
		}
		catch(Exception e)
		{
			System.out.println("Could not read file.");
			System.exit(0);
		}
		
		int fail[] = {-1};
		return fail;
	}
	
	public static void writeArray(int A[])
	{	
		try
		{
			File o = new File("testFile.txt");
			FileOutputStream fos = new FileOutputStream(o); 
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		 
			bw.write(Integer.toString(A.length));
			bw.newLine();
			
			for (int i = 0; i < A.length; i++) 
			{
				bw.write(Integer.toString(A[i]));
				bw.newLine();
			}
		 
			bw.close();
		}
		catch (Exception e) 
		{
			System.out.println("Could not write file.");
		}
	}
	
	public static void main(String[] args)
	{
		
		//Calculate average time.
		long sumTime = 0;
		long avgTime = 0;
		
		long numTrials = 100;
		for(int k = 0;k<numTrials;k++)
		{
			//Create random array.
			int size = 1000000;
			int A[] = new int[size];
			for(int i = 0;i < size;i++)
			{
				Random rand = new Random();
				int randNum = rand.nextInt();
				A[i] = randNum;
			}
			//Write array to file
			//writeArray(A);
			
			//Read array
			//int A[] = readArray("testFile.txt");
			
			//Timer
			final long startTime = System.currentTimeMillis();
			quicksort(A);
			final long endTime = System.currentTimeMillis();
			long totalTime = (endTime-startTime);
			
			sumTime = sumTime + totalTime;
			//writeArray(A);
		}
		avgTime = sumTime/numTrials;
		System.out.println((avgTime/1000.0));
		

		/*///inSort Test///
		int A[] = readArray("testFile.txt");
		inSort(A);
		writeArray(A);
		*/
	}
}
