package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static String p;
	static ArrayList<Integer> arr;
	
	static void calc() {
		// 똑바로인지 거꾸로인지
		boolean right = true;
		
		for (int i = 0; i < p.length(); i++) {
			char function = p.charAt(i);
			
			// 수 순서 뒤집기
			if (function == 'R') {
				right = !right;
			// 첫번째 수 버리기
			} else {
				if (arr.size() == 0) {
					System.out.println("error");
					return;
				}
				if (right) {
					arr.remove(0);
				} else {
					arr.remove(arr.size() - 1);
				}
			}
		}
		
		if (arr.size() > 0 && right) {
			System.out.println(Arrays.toString(arr.toArray()).replace(" ", ""));
			return;
		} else if (arr.size() > 0 && !right) {
			Collections.reverse(arr);
			System.out.println(Arrays.toString(arr.toArray()).replace(" ", ""));
			return;
		} else {
			System.out.println("[]");
			return;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스 개수
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			
			// 수행할 함수
			p = br.readLine();
			// 배열의 수 개수
			n = Integer.parseInt(br.readLine());
			
			// 배열 String으로
			String temp = br.readLine();
			
			// 시작부터 빈 배열일 경우
			if (n == 0) {
				arr = new ArrayList<Integer>();
			} else {
				// 괄호 지우고
				temp = temp.replace("[", "").replace("]", "");
				// 컴마로 나눠서
				String[] tempArr = temp.split(",");
				// 리스트에 넣기
				arr = new ArrayList<Integer>();
								
				for (int i = 0; i < n; i++) {
					arr.add(Integer.parseInt(tempArr[i]));
				}
			}
			
			calc();	
		}
	}
}