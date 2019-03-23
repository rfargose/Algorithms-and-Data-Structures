import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class QuickSort {
	private static int []a; 
	public static void main(String[] args) {
		a = generateArray(); //Will generate an array
		
		printArray(); //Will print the unsorted array
		shuffleArray(a); //The array is shuffled before calling the sort function to avoid the worst-case scenario
		sort(); //Sort function sorts the given array using quicksort
		System.out.println("");
		
		printArray(); //printArray function prints the sorted array

		//The statements below will find the first  minimum difference
		int m1=Integer.MAX_VALUE;
		int p=0,q=0;
		for(int j=0;j<a.length-1;j++)
		{ int difference= Math.abs(a[j]-a[j+1]);
		if(difference<m1) //Executes if the difference between two consecutive elements is less than minimum difference i.e m1
		{
			m1=difference;
			p=a[j];
			q=a[j+1];
		}
		}
		System.out.println(" ");
		System.out.println("First minimum difference is="+m1);
		System.out.println("The pair of elements is="+p+"and"+q);
		//The statements below will find the second minimum difference
		int m2=Integer.MAX_VALUE;
		int r=p,s=q;
		for(int k=0;k<a.length-1;k++)
		{ int differ= Math.abs(a[k]-a[k+1]);
		if(m2>differ && differ>m1) //Executes if the differ is less than second minimum difference i.e m2 and differ is greater than minimum difference i.e m1
		{
			m2=differ;
			r=a[k];
			s=a[k+1];
		}
		}
		if(m2==Integer.MAX_VALUE)
		{m2=m1;}
		System.out.println("Second minimum difference is="+m2);
		System.out.println("The pair of elements is="+r+"and"+s);
	}
	
	//The following function is a helper function which declares and initializes an array 
		private static int[] generateArray() {
			int n;
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter the number of elements in the array");
			n= sc.nextInt();
			if(n<2) { System.out.println("0");} 
			else {
				int []array = new int[n];
				System.out.println("Enter the array elements");
				for(int i=0;i<n;i++){
					array[i] = sc.nextInt(); 
				}
				return array;
			}
			return a;
		}

		//The following function is a helper function which prints an array 
		private static void printArray() {
			for(int i : a){
				System.out.print(i+" ");
			}
			}


//The following function shuffles the array to avoid worst-case scenario in quicksort
	private static void shuffleArray(int[] a2) {
		Random rnd = ThreadLocalRandom.current();
	    for (int i = a.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	     swap(index,i);
	    }
	}

	private static void sort() {
		int low = 0;
		int high = a.length-1;
		quickSort1(low, high);
	}
	
	private static void quickSort1(int low, int high) {
		if(low >= high) //Executed only when the low is less than high 
			return;
		int pivot = a[high]; //Here, I've considered rightmost i.e element at high index as the pivot
		int partition = partition(low, high, pivot);
		//The following function calls quicksort1() recursively for subarrays
		quickSort1(0, partition-1);
		quickSort1(partition+1,high);
	}
	// The following method partitions the given array and will return the integer which points to the sorted pivot index
	private static int partition(int low,int high,int pivot){
		int lowPointer = low-1;
		int highPointer = high;
		while(lowPointer < highPointer){
			while(a[++lowPointer] < pivot);
			while(highPointer > 0 && a[--highPointer] > pivot);
			if(lowPointer >= highPointer){
				break;
			}else{
				swap(lowPointer, highPointer);
			}
		}
		swap(lowPointer, high);
		return lowPointer;


	}
// The following is a helper function that swaps the item on the left greater than pivot with item on the right less than pivot
	private static void swap(int leftCursor, int rightCursor) {
		int temp = a[leftCursor];
		a[leftCursor] = a[rightCursor];
		a[rightCursor] = temp;


	}
	

	}

