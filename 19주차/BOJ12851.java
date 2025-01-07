import java.io.*;
import java.util.*;

public class Main {
	
	static int N, K, cnt;
	static Queue<Integer> queue;
	static int[] visited;
	
	static void bfs() {
		while(!queue.isEmpty()) {
			int place = queue.poll();
			
			// 도착하면 경우의 수 증가
			if (place == K) {
				cnt++;
			}
			
			if (place - 1 >= 0 && (visited[place - 1] == 0 || visited[place - 1] >= visited[place] + 1)) {
				queue.add(place - 1);
				visited[place - 1] = visited[place] + 1;
			}
			if (place + 1 <= 100000 && (visited[place + 1] == 0 || visited[place + 1] >= visited[place] + 1)) {
				queue.add(place + 1);
				visited[place + 1] = visited[place] + 1;
			}
			if (place * 2 <= 100000 && (visited[place * 2] == 0 || visited[place * 2] >= visited[place] + 1)) {
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
        cnt = 0;
        
        // 동생이랑 같은 위치에 있다면 bfs 불필요
        if (N != K) {
        	bfs();
        } else {
        	cnt = 1;
        }
        
        System.out.println(visited[K]);   
        System.out.println(cnt);
    }
}