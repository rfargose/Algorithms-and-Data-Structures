import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class LongSubarray {
	private static int []a; 
	public static void main(String[] args) {
		a = generateArray(); //Will generate an array
	    shuffleArray(a); //The array is shuffled before calling the sort function to avoid the worst-case scenario
		sort(); //Sort function sorts the given array using quicksort
		int x;
		Scanner s=new Scanner(System.in);
		System.out.println(" ");
		System.out.println("Enter the value of x");
		x= s.nextInt();

		int l= subArray(0,a.length-1,x);
		System.out.println("longest subarray length="+l);    

	}

//The following function finds the longest sub array whose size>=x
	private static int subArray(int i, int j, int x) {
		int median;
		if(j-i % 2 == 0) //Here, j-i gives the length of current sub array 
		{
			median = a[(j-i)/2 + i]; //if array elements are odd
		}else {
			median = (a[(j-i)/2 + i] + a[(j-i + 1)/2 + i])/2; // if array elements are even
		}
		if(median>=x) {

			return j-i+1; // Gives the length of largest sub array whose size>=x
		}
		else
		{
			return subArray(i+1,j,x); //Recursively computes for smaller sub arrays
		}
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
