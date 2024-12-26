import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static char[][] map;
	
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < N) {
			return true;
		}
		return false;
	}
	
	static int bfs(int x, int y) {
		int cnt = 0;
		
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {x, y});
		map[x][y] = '0';
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			cnt++;
			
			for (int d = 0; d < 4; d++) {
				int nx = temp[0] + dx[d];
				int ny = temp[1] + dy[d];
				if (isIn(nx, ny) && map[nx][ny] == '1') {
					queue.add(new int[] {nx, ny});
					map[nx][ny] = '0';
				}
			}
		}
		return cnt;
	}
	
    public static void main(String[] args) throws Exception {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
        // 지도 크기
        N = Integer.parseInt(br.readLine());
        
        // 지도
        map = new char[N][N];
        
        for (int i = 0; i < N; i++) {
        	String temp = br.readLine();
        	for (int j = 0; j < N; j++) {
        		map[i][j] = temp.charAt(j);
        	}
        }
        
        // 단지수
        int danjiCnt = 0;
        // 단지내 집 수
        ArrayList<Integer> cnts = new ArrayList<Integer>();
        
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
        		if (map[i][j] == '1') {
        			danjiCnt++;
        			int tempCnt = bfs(i, j);
        			cnts.add(tempCnt);
        		}
        	}
        }
                
        System.out.println(danjiCnt);
        
        Collections.sort(cnts);
        
        for (int cnt : cnts) {
        	System.out.println(cnt);
        }
    }
}