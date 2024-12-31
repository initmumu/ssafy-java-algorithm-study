import java.io.*;
import java.util.*;

public class Main {
	
	static int N, R, Q;
	static ArrayList<Integer>[] tree;
	static int[] sizes;
	static boolean[] visited;
	
	// 서브트리 크기 구하는 배열
	static int getSizes(int node) {
		
		visited[node] = true;
		int cnt = 1;
		
		for (int next : tree[node]) {
			if (!visited[next]) {
				cnt += getSizes(next);
			}
		} 
		
		sizes[node] = cnt;
		
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 트리의 정점 수
		N = Integer.parseInt(st.nextToken());
		// 루트의 번호
		R = Integer.parseInt(st.nextToken());
		// 쿼리의 수
		Q = Integer.parseInt(st.nextToken());
		
		// 인접 리스트
		tree = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			st =  new StringTokenizer(br.readLine());
			int U = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			tree[U].add(V);
			tree[V].add(U);
		}
		
		// 서브트리 크기 저장할 배열
		sizes = new int[N + 1];
		
		// 방문 여부
		visited = new boolean[N + 1];
		
		// 서브트리 크기 계산
		getSizes(R);
		
		// 정답들
		for (int i = 0; i < Q; i++) {
			int ansInput = Integer.parseInt(br.readLine());
			System.out.println(sizes[ansInput]);
		}
	}
}