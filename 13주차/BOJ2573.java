package algorithm;

import java.io.*;
import java.util.*;
 
public class Main {
	
	static int N, M;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	// 범위 내에 있는지 확인
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < M) {
			return true;
		}
		return false;
	}
	
	// 4방향 bfs
	static int[][] bfs(int[][] array, int x, int y) {
		
		int[][] array2 = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				array2[i][j] = array[i][j];
			}
		}
		
		Queue<int[]> queue = new ArrayDeque<>();
		int[] temp = {x, y};
		queue.add(temp);
		array2[x][y] = 0;
		
		while (!queue.isEmpty()) {
			temp = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp[0] + dx[d];
				int ny = temp[1] + dy[d];
				if (isIn(nx, ny) && array2[nx][ny] != 0) {
					int[] temp2 = {nx, ny};
					queue.add(temp2);
					array2[nx][ny] = 0;
				}
			}
		}
		
		return array2;
	}
	
	// 덩어리 개수 세기
	static int count(int[][] array) {
		
		int cnt = 0;
				
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (array[i][j] != 0) {
					array = bfs(array, i, j);
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	// 녹이기
	static int[][] melt(int[][] array) {
		
		int[][] array2 = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (array[i][j] != 0) {
					int cnt = 0;
					for (int d = 0; d < 4; d++) {
						if (array[i + dx[d]][j + dy[d]] == 0) {
							cnt++;
						}
					}
					if (cnt > array[i][j]) {
						array2[i][j] = 0;
					} else {
						array2[i][j] = array[i][j] - cnt;
					}
				}
			}
		}
		
		return array2;
	}
	
	// 다 녹아버렸나 확인
	static boolean gone(int[][] array) {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (array[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}
	
    public static void main(String[] args) throws Exception{
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 행렬 개수
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        // 빙산 정보
        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < M; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        int time = 0;
        
        // 빙산이 두 덩어리 이상이면 멈추기, 다 녹아버리면 0 출력
        while (true) {
        	if (gone(arr)) {
        		time = 0;
        		break;
        	}
        	int cnt = count(arr);
        	if (cnt >= 2) {
        		break;
        	}
        	arr = melt(arr);
        	time++;
        }

        System.out.println(time);
    }
}