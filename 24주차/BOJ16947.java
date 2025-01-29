package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static boolean[] visited;
	static int[] ans;
	static ArrayList<Integer>[] list;
	static Queue<Integer> queue;
	
	// 현재 탐색 중인 노드, 몇개의 역 지나왔는지, dfs 탐색 시작한 노드
	static void cycle(int cur, int cnt, int start) {
		
		// 현재 노드 방문 처리
		visited[cur] = true;
		
		// 현재 노드와 연결된 모든 노드 탐색
		for (int node : list[cur]) {
			// 방문 전 노드면 킵고잉
			if (!visited[node]) { 
				cycle(node, cnt + 1, start); 
				
			// 사이클 찾은 경우 : 시작 노드로 돌아온 경우
			} else if (node == start && cnt >= 3) {
				// 사이클에 포함되는 노드 : 거리 0 설정
				ans[node] = 0;
				// bfs 위해 담음
				queue.add(node);
				return;
			}
		}
	}
	
	// 각 역에서 순환선까지 거리 계산하는 메서드
	static void bfs() {

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int next: list[cur]){
            	// 아직 방문하지 않은 역이면
                if(ans[next] == -1){
                	// 현재 역의 거리 + 1
                    ans[next] = ans[cur] + 1;
                    queue.offer(next);
                }
            }
        }
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 역의 개수
		int N = Integer.parseInt(br.readLine());
		
		// 정답 담을 배열 (-1로 초기화)
		ans = new int[N + 1];
		
		for (int i = 0; i < N + 1; i++) {
			ans[i] = -1;
		}
		
		// 양방향 구간 정보 담음
		list = new ArrayList[N + 1];
		
		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < N; i++ ) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		// bfs 위한 큐
		queue = new ArrayDeque<Integer>();
		
		// 모든 역에서 한번씩 사이클 찾기
		for (int i = 1; i < N + 1; i++) {
			visited = new boolean[N + 1];
			cycle(i, 1, i);
		}
		
		bfs();
		
		StringBuilder sb = new StringBuilder();
		
		// 정답 출력
		for (int i = 1; i < N + 1; i++) {
			sb.append(ans[i]).append(" ");
		}
		
		System.out.println(sb);
	}
}