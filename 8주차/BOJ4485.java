import java.io.*;
import java.util.*;

public class BOJ4485 {
	
	static int N;
	
	static int[][] map;
	static int[][] distance;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static final int INF = Integer.MAX_VALUE;
	
	static class Edge implements Comparable<Edge> {
        int x, y, weight;

        Edge(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int t = 1; true; t++) {
			N = Integer.parseInt(br.readLine());
			
			if (N == 0) {
				break;
			}
			
			map = new int[N][N];
			distance = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					distance[i][j] = INF;
				}
			}
			
			Dijkstra();
			System.out.println("Problem" + " " + t + ": " + distance[N-1][N-1]);
		}
		
	}
	
	static void Dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, 0, map[0][0]));
		distance[0][0] = map[0][0];
		
		while (!pq.isEmpty()) {
			Edge current = pq.poll();
			int x = current.x;
			int y = current.y;
            int currentDist = current.weight;
            
            if (currentDist > distance[x][y]) {
                continue;
            }
            
            for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				
				int weight = map[nx][ny];
				
				if (distance[x][y] + weight < distance[nx][ny]) {
					distance[nx][ny] = distance[x][y] + weight;
					pq.add(new Edge(nx, ny, map[nx][ny]));
				}
			}
		}
	}
}
