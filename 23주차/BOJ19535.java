package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static long cntD, cntG;
	static ArrayList<Integer>[] tree;
	static ArrayList<int[]> edges;
	
	// D-트리 개수 세기
	static void findD() {
		
		for (int i = 0; i < N - 1; i++) {
			int[] edge = edges.get(i);
			long left = tree[edge[0]].size() - 1;
			long right = tree[edge[1]].size() - 1;
			cntD += (left * right);
		}
	}
	
	// G-트리 개수 세기
	static void findG() {
				
		for (int i = 1; i < N + 1; i++) {
			long sizeT = tree[i].size();
			if (sizeT >= 3) {
				long tempCnt = sizeT * (sizeT - 1) * (sizeT - 2);
				cntG += (tempCnt / 6);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 트리의 정점 수
		N = Integer.parseInt(br.readLine());
		
		// 인접 리스트
		tree = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		// 간선 리스트
		edges = new ArrayList<int[]>();
		
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
			edges.add(new int[] {a, b});
		}
		
		// D-트리 개수
		cntD = 0;
		// G-트리 개수
		cntG = 0;
		
		findD();
		findG();
				
		// 정답 판별
		if (cntD > 3 * cntG) {
			System.out.println('D');
		} else if (cntD < 3 * cntG) {
			System.out.println('G');
		} else {
			System.out.println("DUDUDUNGA");
		}
	}
}