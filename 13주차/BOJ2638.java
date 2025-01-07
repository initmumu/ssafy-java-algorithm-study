package algorithm;

import java.io.*;
import java.util.*;
 
public class Main {
	
	static int N, M;
	static int[][] arr;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static boolean[][] visited;
	static Queue<int[]> queue;
	static Queue<int[]> queue2;
	
	// 배열 내부인지 여부 반환
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < M) {
			return true;
		}
		return false;
	}
	
	// 가장자리인지 여부 반환
	static boolean isBoundary(int x, int y) {
		if (x == 0 || x == N - 1 || y == 0 || y == M - 1) {
			return true;
		}
		return false;
	}
	
	// 공기와 접촉하는 지 여부 반환
	static boolean isOut(int x, int y) {
		int cnt = 0;

		queue2.clear();
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (isIn(nx, ny) && arr[nx][ny] == 0) {
				int[] temp = {nx, ny};
				queue2.add(temp);
			}
		}
		int len = queue2.size();
		for (int a = 0; a < len; a++) {
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					visited[i][j] = false;
				}
			}
			
			int[] te = queue2.poll();
			visited[te[0]][te[1]] = true;
			queue.add(te);
			
			while (!queue.isEmpty()) {
				int[] temp = queue.poll();
				int tx = temp[0];
				int ty = temp[1];
				
				for (int d = 0; d < 4; d++) {
					int nx = tx + dx[d];
					int ny = ty + dy[d];
					if (isIn(nx, ny) && arr[nx][ny] == 0 && !visited[nx][ny]) {
						int[] t = {nx, ny};
						queue.add(t);
						visited[nx][ny] = true;
						if (isBoundary(nx, ny)) {
							cnt++;
							queue.clear();
							break;
						}
					}
				}
			}
		}
		
		// 2변 이상 접촉하면 true 반환
		if (cnt >= 2) {
			return true;
		}
				
		return false;
	}
	
	// 모두 녹았는지 여부 반환
	static boolean isMelt() {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}
	
    public static void main(String[] args) throws Exception{
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 모눈종이 크기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        // 공간 초기 정보
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < M; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
		queue = new ArrayDeque<>();
		queue2 = new ArrayDeque<>();
		visited = new boolean[N][M];
        
        int time = 0;
                
        while (true) {
        	
        	if (isMelt()) {
        		break;
        	}
        	
        	// 녹을 치즈 정보 담기
        	List<int[]> list = new ArrayList<>();
        	for (int i = 0; i < N; i++) {
        		for (int j = 0; j < M; j++) {
        			if (arr[i][j] == 1 && isOut(i, j)) {
        				int[] temp = {i, j};
        				list.add(temp);
        			}
        		}
        	}
        	        	
        	// 녹이기
        	for (int i = 0; i < list.size(); i++) {
        		int x = list.get(i)[0];
        		int y = list.get(i)[1];
        		arr[x][y] = 0;
        	}
        	time++;
        }
        
        System.out.println(time);
    }
}