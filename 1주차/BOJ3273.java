import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
	Scanner sc = new Scanner(System.in);
	
	int n = sc.nextInt();
	
	int[] arr = new int[n];
	
	for (int i = 0; i < n; i++) {
		arr[i] = sc.nextInt();
	}
	Arrays.parallelSort(arr);
	
	int x = sc.nextInt();
	int cnt = 0;
	int start = 0;
	int end = n - 1;
	int sum = 0;
		
	while (start < end) {
		sum = arr[start] + arr[end];
		if (sum == x) {
			cnt++;
			start++;
		}
		if (sum < x) {
			start ++;
		}
		if (sum > x) {
			end--;
		}
	}
	
	System.out.println(cnt);
	}

}