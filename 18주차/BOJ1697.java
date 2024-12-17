package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K;
	static Queue<Integer> queue;
	static int[] visited;
	
	static void bfs() {
		while(!queue.isEmpty()) {
			int place = queue.poll();
			
			if (place == K) {
				return;
			}
			
			if (place - 1 >= 0 && visited[place - 1] == 0) {
				queue.add(place - 1);
				visited[place - 1] = visited[place] + 1;
			}
			if (place + 1 <= 100000 && visited[place + 1] == 0) {
				queue.add(place + 1);
				visited[place + 1] = visited[place] + 1;
			}
			if (place * 2 <= 100000 && visited[place * 2] == 0) {
				queue.add(place * 2);
				visited[place * 2] = visited[place] + 1;
			}
		}	
		return;
	}
	
    public static void main(String[] args) throws Exception {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 수빈이 위치 N, 동생 위치 K
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        queue = new ArrayDeque<Integer>();
        queue.add(N);
        
        visited = new int[100001];
        
        bfs();
        
        System.out.println(visited[K]);        
    }
}