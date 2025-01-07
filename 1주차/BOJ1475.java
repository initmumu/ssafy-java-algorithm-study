import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String room = sc.next();
		
		int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		for (int i = 0; i < room.length(); i++) {
			int n = room.charAt(i) - '0';
			if (n == 9) {
				nums[6] += 1;
			} else {
				nums[n] += 1;
			}
		}
		
		nums[6] = (nums[6] / 2) + (nums[6] % 2);
		
		int max = Integer.MIN_VALUE;
		
		for (int n : nums) {
			if (n > max) {
				max = n;
			}
		}
		
		System.out.println(max);
	}
	
}
