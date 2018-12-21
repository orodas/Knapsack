//Oscar Rodas


import java.util.*;
import java.text.*;
import java.io.*;

public class Knapsack
{
	public static int[][] chart;
	public static int[][] data = new int[24][2];
	public static int capacity = 1;
	public static Scanner keyboard = new Scanner(System.in);
	public static Scanner file = null;
	public static int[][] keep;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		if(args.length < 1)
		{
			System.out.println("Usage: arg1");		//arg1 is the input file
			System.exit(1);
		}
		
		file = new Scanner(new File(args[0]));
		
		System.out.println("Enter the capacity of the knapsack");
		capacity = keyboard.nextInt();
		if(capacity < 0)
			capacity = capacity * -1;
		
		chart = new int[24][capacity+1];
		keep = new int[24][capacity+1];
		
		int a = 1;
		while(file.hasNext())
		{
			String s = file.nextLine();
			if(!s.equals(""))
			{
				String[] str = s.trim().split("\\s+");
				data[a][0] = Integer.parseInt(str[0]);		//value of object a
				data[a][1] = Integer.parseInt(str[1]);		//weight of object a
				a++;
			}
		}
		
		for(int w = 0; w < capacity+1; w++)
		{
			chart[0][w] = 0;
		}
		
		for(int i = 1; i < 24; i++)
		{
			for(int w = 1; w < capacity+1; w++)
			{
				if(data[i][1] > w)
				{
					chart[i][w] = chart[i-1][w];
				}
				else
				{
					chart[i][w] = Math.max(chart[i-1][w], data[i][0] + chart[i-1][w-data[i][1]]);
					if(data[i][0] + chart[i-1][w-data[i][1]] > chart[i-1][w])	//if the object is taken, keep track of it
						keep[i][w] = 1;
				}
			}
		}
		
		System.out.println("The maximum value is: " + chart[23][capacity]);
		
		int k = capacity;
		for(int i = 23; i > 0;  i--)
		{
			if(keep[i][k] == 1)
			{
				System.out.print("object " + i + ", ");
				k = k - data[i][1];		//capacity remaining after taking object i
			}
		}
			
	}

}