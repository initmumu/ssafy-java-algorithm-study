package algorithm;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 압축된 문자열
		String compressed = br.readLine();
		
		// 압축된 문자열을 스택에 담음
		Stack<Integer> lenStack = new Stack<Integer>();
		Stack<Integer> gopStack = new Stack<Integer>();
		
		// 압축 풀어진 문자열 길이
		int ans = 0;
		
		for (int i = 0; i < compressed.length(); i++) {
			char now = compressed.charAt(i);
			
			if (now == '(') {
				// 직전 문자가 반복 횟수일 것임
				gopStack.push(compressed.charAt(i - 1) - '0');
				// 현재까지 계산된 길이를 저장하고, 괄호 안 계산 위해 ans 초기화
				lenStack.push(ans);
				ans = 0;
			} else if (now == ')') {
				// 괄호가 열리기 전의 길이 + (괄호 안 길이 * 반복 횟수)
				int gop = gopStack.pop();
				ans = lenStack.pop() + (ans * gop);
			} else {
				// 해당 숫자가 ( 전 숫자, 즉 반복 횟수라면 건너뛰기
				if (i + 1 < compressed.length() && compressed.charAt(i + 1) == '(') {
					continue;
				}
				ans++;
			} 
		}
		
		System.out.println(ans);
	}
}