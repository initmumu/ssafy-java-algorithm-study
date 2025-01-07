package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, K, ans;
	static String[] words;
	static boolean[] visited;
	static boolean[] letters;
	
	// 글자 조합으로 판단
	static void wordCnt(int depth, int start) {
		
		// 김지민이 가르칠 수 있는 글자 개수에 도달하면
		if (depth == K - 5) {
			int tempCnt = 0;
			// 몇개 단어 읽을 수 있는지 계산
			for (int i = 0; i < N; i++) {
				String word = words[i];
				boolean ok = true;
				for (int j = 0; j < word.length(); j++) {
					if (!visited[word.charAt(j) - 'a']) {
						ok = false;
					}
				}
				
				// 읽을 수 있다면 개수 증가
				if (ok) {
					tempCnt++;
				}
			}
			
			// 최댓값 갱신
			ans = Integer.max(ans, tempCnt);
			return;
		}
		
		// 중복된 글자 없이, 조합의 중복 없이 뽑기
		for (int i = start; i < 26; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				wordCnt(depth + 1, i + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 남극언어의 단어 개수
		N = Integer.parseInt(st.nextToken());
		// 김지민이 가르칠 시간 있는 글자 개수
		K = Integer.parseInt(st.nextToken());
		
		// 단어 목록
		words = new String[N];
		
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}
		
		// 고른 글자 배열
		letters = new boolean[26];
		
		// 방문 배열
		visited = new boolean[26];
		
		visited['a' - 'a'] = true;
		visited['n' - 'a'] = true;
		visited['t' - 'a'] = true;
		visited['i' - 'a'] = true;
		visited['c' - 'a'] = true;
				
		// 정답
		ans = 0;
		
		if (K < 5) {
			System.out.println(0);
		} else if (K == 26) {
			System.out.println(N);
		} else {
			wordCnt(0, 0);
			System.out.println(ans);
		}
	}
}