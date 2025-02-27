package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 피연산자 개수
		int N = Integer.parseInt(br.readLine());
		
		// 후위표기식
		String sick = br.readLine();
		
		// 숫자
		double[] nums = new double[N];
		
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			nums[i] = n;
		}
		
		Stack<Double> stack = new Stack<Double>();
		
		for (int i = 0; i < sick.length(); i++) {
			char temp = sick.charAt(i);
			if (temp == '+' || temp == '-' || temp == '*' || temp == '/') {
				double b = stack.pop();
				double a = stack.pop();
				double tempAns;
				if (temp == '+') {
					tempAns = a + b;
				} else if (temp == '-') {
					tempAns = a - b;
				} else if (temp == '*') {
					tempAns = a * b;
				} else {
					tempAns = a / b;
				}
				stack.add(tempAns);
			} else { 
				stack.add(nums[temp - 65]);
			}
		}

		System.out.printf("%.2f", stack.pop());
	}
}	