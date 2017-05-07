package org.koushik.javabrains.messenger.resources;

public class BubbleSort {
	public static void bubbleSort(int[] numArray) {

	    int n = numArray.length;
	    int temp = 0;

	    for (int i = 0; i < n; i++) {
	        for (int j = 1; j < (n - i); j++) {

	            if (numArray[j - 1] > numArray[j]) {
	                temp = numArray[j - 1];
	                numArray[j - 1] = numArray[j];
	                numArray[j] = temp;
	            }

	        }
	    }

	}
	
	public static void main (String [] args) {
		int [] arr = {2, 5, 1, 6, 3, 9, 4};
		bubbleSort(arr);
	    for (int i=0; i< arr.length; i++) {
	    	System.out.println(arr[i]);
	    }
	}
}
