import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[][] map;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int cheese = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					cheese++;
				}
			}
		}

		int time = 0;
		while (cheese > 0) {
			melt();
			time++;
		}
		
		System.out.println(time);
	}
	
	static void melt() {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0, 0});
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			int x = pos[0], y = pos[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
					continue;
				}
				
				if (map[nx][ny] == 0) {
					visited[nx][ny] = true;
					queue.offer(new int[] {nx, ny});
				}
			}
		}
		
		List<int[]> list = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                            continue;
                        }

                        if (visited[nx][ny]) {
                            cnt++;
                        }
                    }
                    if (cnt >= 2) {
                    	list.add(new int[] {i, j});
                    }
                }
            }
        }
        
        for (int[] cur : list) {
        	int i = cur[0];
            int j = cur[1];
             
            if (--map[i][j] == 0) {
                cheese--;
            }
		}
	}
}
