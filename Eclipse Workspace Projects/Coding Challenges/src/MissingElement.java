
public class MissingElement {
public static int[] ARRAY= {1,2,4,5,6,7};

public static void findMin(int[] arr)
{
	int sum = 0;
	int idx = 0;
	for (int i = 0; i < arr.length; i++) {
	    if (arr[i] == 0) {
	         idx = -1; 
	    } else {
	         sum += arr[i];
	    }
	}

	// the total sum of numbers between 1 and arr.length.
	int total = ((arr.length + 1) * arr.length)/ 2;

	System.out.println("missing number is: " + (total - sum) + " at index " + idx);
}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		findMin(ARRAY);
		// TODO Auto-generated method stub

	}

}
