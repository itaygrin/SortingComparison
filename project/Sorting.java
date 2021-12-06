import java.util.Arrays;
import java.util.Random;

import Plotter.Plotter;


public class Sorting {

	final static int BUBBLE_VS_QUICK_LENGTH = 12;
	final static int MERGE_VS_QUICK_LENGTH = 15;
	final static int BUBBLE_VS_QUICK_SORTED_LENGTH = 10;
	final static int WIERD_VS_BUBBLE = 7;
	final static double T = 600.0;
	/**
	 * Sorts a given array using the quick sort algorithm.
	 * At each stage the pivot is chosen to be the rightmost element of the subarray.
	 * 
	 * Should run in average complexity of O(nlog(n)), and worst case complexity of O(n^2)
	 * 
	 * @param arr - the array to be sorted
	 */
	public static void quickSortArbitraryPivot(double[] arr){
		quickSort(arr, 0, arr.length-1);
	}

	//The function creates a partition by choosing a pivot to be the last element.
	//Then it quicksorts recursively both sides of the partition (elements smaller than the pivot and greater).
	private static void quickSort(double[] arr, int low, int high) {
		if (low < high){
			int partition = newPartition (arr, low, high); //index of partition
			quickSort(arr, low, partition - 1);
			quickSort(arr, partition + 1, high);
		}
	}

	//The function picks an element to be the pivot (last element) and swaps elements accordinally.
	//Smaller elements to the left of the pivot, and Greater elements to the right.
	//returns: the index of the partiotion.
	private static int newPartition(double[] arr, int low, int high){
		double pivot = arr[high];
		int index = low -1;
		for (int i = low; i <= high - 1; i++) {
			if (arr[i] < pivot){
				index++;
				swap(arr, i, index);

			}
		}
		swap(arr, index+1, high);
		return index+1;

	}

	//Swaps elements.
	private static void swap(double[] arr, int i, int j){
		double temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	/**
	 * Sorts a given array using the weird sort algorithm, seen in the recitation.
	 * 
	 * Should run in worst (and best) time complexity of O(n^(log_(3/2)(3)).
	 * 
	 * @param arr - the array to be sorted
	 */

	//The function divides a big enough array (>10 elements) to 1/3 and 2/3.
	//It will keep dividing the array to smaller segments of third and 2 thirds and sort them,
	//until it reaches a small enough input which then will sort with bubble sort.
	public static void weirdSort(double[] arr) {
		weirdSort(arr, 0, arr.length);
	}
	public static void weirdSort(double[] arr, int start, int end) {
		if (end-start+1 > 10){
			int firstThird = end/3;
			int secondThird = firstThird*2;
			weirdSort(arr, start, secondThird);
			weirdSort(arr, firstThird, end-1);
			weirdSort(arr, start, secondThird);
		}
		else bubbleSort(arr);
	}

	
	/**
	 * Sorts a given array using the merge sort algorithm.
	 * 
	 * Should run in complexity O(nlog(n)) in the worst case.
	 * 
	 * @param arr - the array to be sorted
	 */

	//The function calls itself recursively to divide itself to smaller arrays.
	//When small enough, it sorts them  and then merges them back in sorted.
	//mergeSort uses a help function, merge which merges the arrays back in.
	public static void mergeSort(double[] arr){
/*		for (double var:arr) {
			System.out.print(var+ ", ");
		}*/
		System.out.println();
		mergeSort(arr, 0, arr.length-1);
/*		for (double var:arr) {
			System.out.print(var+ ", ");
		}*/

	}

	private static void mergeSort(double[] arr, int start, int end){
		int mid;
		if (start < end){
			mid = (start + end)/2;
			mergeSort(arr, start, mid);
			mergeSort(arr, mid + 1, end);
			merge(arr, start, end, mid);
		}
	}


	private static void merge(double[] arr, int start, int end, int mid){
		double[] left = new double[mid - start + 1];
		double[] right = new double[end - (mid + 1) + 1];
		int c = 0;
		for (int i = start; i <= mid; i++) {
			left[c] = arr[i];
			c++;
		}
		c = 0;
		for (int i = mid + 1; i <= end; i++) {
			right[c] = arr[i];
			c++;
		}
		int i = 0;
		int j = 0;
		c = start;
		while(i < left.length && j < right.length){
			if(left[i] <= right[j]){
				arr[c] = left[i];
				i++;
			} else {
				arr[c] = right[j];
				j++;
			}
			c++;
		}
		while(i < left.length){
			arr[c] = left[i];
			i++;
			c++;
		}
		while(j < right.length){
			arr[c] = right[j];
			j++;
			c++;
		}
	}

	/*
		int first = mid - start + 1;
		int sec = end - end;
		double[] left = new double[first+1];
		double[] right = new double[sec+1];
		int count=0;
		for (int i = start; i <= mid; i++) { //set left array
			left[count] = arr[i];
			count++;
		}
		count = 0;
		for (int i = mid+1; i <= end ; i++) {  ////set right array
			right[count] = arr[i];
			count++;
		}
		left[first] = Double.MAX_VALUE;
		right[sec] = Double.MAX_VALUE;
		int i = 1;
		int j = 1;
		for (int k = start; k < mid; k++) {
			if (left[i] <= right[j]){
				arr[k] = left[i];
				i++;
			}
			else {
				arr[k] = right[j];
				j++;
			}
		}
*/




	/**
	 * Sorts a given array using bubble sort.
	 * If at any time the algorithm recognizes no more inversions are needed it should stop.
	 * 
	 * The algorithm should run in complexity O(n^2) in the worst case.
	 * The algorithm should run in complexity O(n) in the best case.
	 * 
	 * @param arr - the array to be sorted
	 */

	//Compares every 2 values in an array and swaps them accoridnaly. The function
	//keeps doing so until no swap is found.
	public static void bubbleSort(double[] arr){
		double min = arr[0];
		boolean swap;
		for (int i = 0; i < arr.length-1; i++) {
			swap = false;
			for (int j = 0; j < arr.length-i-1; j++) {
				if (arr[j] > arr[j+1]){
					swap(arr, j, j+1);
					swap = true;
				}

			}
			if (!swap) return;
		}

	}
	
	public static void main(String[] args) {
		bubbleVsQuick();
		mergeVsQuick();
		bubbleVsQuickOnSortedArray();
		wierdVsBubble();
	}
	
	/**
	 * Compares the selection sort algorithm against quick sort on random arrays
	 */
	public static void bubbleVsQuick(){
		double[] quickTimes = new double[BUBBLE_VS_QUICK_LENGTH];
		double[] bubbleTimes = new double[BUBBLE_VS_QUICK_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < BUBBLE_VS_QUICK_LENGTH; i++) {
			long sumQuick = 0;
			long sumSelection = 0;
			for(int k = 0; k < T; k++){
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				quickSortArbitraryPivot(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				bubbleSort(b);
				endTime = System.currentTimeMillis();
				sumSelection += endTime - startTime;
			}
			quickTimes[i] = sumQuick/T;
			bubbleTimes[i] = sumSelection/T;
		}
		Plotter.plot("quick sort on random array", quickTimes, "bubble sort on random array", bubbleTimes);
	}
	
	/**
	 * Compares the merge sort algorithm against quick sort on random arrays
	 */
	public static void mergeVsQuick(){
		double[] quickTimes = new double[MERGE_VS_QUICK_LENGTH];
		double[] mergeTimes = new double[MERGE_VS_QUICK_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < MERGE_VS_QUICK_LENGTH; i++) {
			long sumQuick = 0;
			long sumMerge = 0;
			for (int k = 0; k < T; k++) {
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				quickSortArbitraryPivot(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				mergeSort(b);
				endTime = System.currentTimeMillis();
				sumMerge += endTime - startTime;
			}
			quickTimes[i] = sumQuick/T;
			mergeTimes[i] = sumMerge/T;
		}
		Plotter.plot("quick sort on random array", quickTimes, "merge sort on random array", mergeTimes);
	}

	/**
	 * Compares the merge sort algorithm against quick sort on pre-sorted arrays
	 */
	public static void bubbleVsQuickOnSortedArray(){
		double[] quickTimes = new double[BUBBLE_VS_QUICK_SORTED_LENGTH];
		double[] bubbleTimes = new double[BUBBLE_VS_QUICK_SORTED_LENGTH];
		long startTime, endTime;
		for (int i = 0; i < BUBBLE_VS_QUICK_SORTED_LENGTH; i++) {
			long sumQuick = 0;
			long sumBubble = 0;
			for (int k = 0; k < T; k++) {
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = j;
					b[j] = j;
				}
				startTime = System.currentTimeMillis();
				quickSortArbitraryPivot(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				bubbleSort(b);
				endTime = System.currentTimeMillis();
				sumBubble += endTime - startTime;
			}
			quickTimes[i] = sumQuick/T;
			bubbleTimes[i] = sumBubble/T;
		}
		Plotter.plot("quick sort on sorted array", quickTimes, "bubble sort on sorted array", bubbleTimes);
	}

	/**
	 * Compares the weird sort algorithm against the bubble sort algorithm
	 */
	public static void wierdVsBubble(){
		double[] weirdTimes = new double[WIERD_VS_BUBBLE];
		double[] bubbleTimes = new double[WIERD_VS_BUBBLE];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < WIERD_VS_BUBBLE; i++) {
			long sumWierd = 0;
			long sumBubble = 0;
			for (int k = 0; k < T; k++) {
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				weirdSort(a);
				endTime = System.currentTimeMillis();
				sumWierd += endTime - startTime;
				startTime = System.currentTimeMillis();
				bubbleSort(b);
				endTime = System.currentTimeMillis();
				sumBubble += endTime - startTime;
			}
			weirdTimes[i] = sumWierd/T;
			bubbleTimes[i] = sumBubble/T;
		}
		Plotter.plot("weird sort", weirdTimes, "bubble sort", bubbleTimes);
	}
	

}
