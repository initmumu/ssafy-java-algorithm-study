package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, candidateLen, maxAns;
	static int[][] map;
	static ArrayList<int[]> candidates, virus;
	static boolean[] visited;
	static int[] arr;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	// 범위 내부인지
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < M) {
			return true;
		}
		return false;
	}
	
	// 안전 영역 크기 구하기
	static int safeZone(int[][] arr) {
		
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	// 바이러스 퍼뜨리기
	static int[][] spreadVirus(int[][] map, int a, int b) {
		
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {a, b});
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x = temp[0];
			int y = temp[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (isIn(nx, ny) && map[nx][ny] == 0) {
					map[nx][ny] = 2;
					queue.add(new int[] {nx, ny});
				}
			}
		}
		
		return map;
	}
	
	// 벽 조합
	static void combi(int depth, int start) {
		
		if (depth == 3) {
			// 배열 복사
			int[][] walledMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					walledMap[i][j] = map[i][j];
				}
			}
			
			// 벽 세우기
			for (int i = 0; i < 3; i++) {
				int x = candidates.get(arr[i])[0];
				int y = candidates.get(arr[i])[1];
				walledMap[x][y] = 1;
			}
			
			// 바이러스 퍼뜨리기
			for (int[] vir : virus) {
				walledMap = spreadVirus(walledMap, vir[0], vir[1]);
			}
			
			// 안전 영역 크기 구하기
			int safeCnt = safeZone(walledMap);
			
			// 안전 영역 크기 최대화
			maxAns = Integer.max(maxAns, safeCnt);
			
			return;
		}
		
		for (int i = start; i < candidateLen; i++) {
			if (!visited[i]) {
				visited[i] = true;
				arr[depth] = i;
				combi(depth + 1, i + 1);
				visited[i] = false;
			}
		}
		
	}

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 지도의 크기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 벽 세울 위치 후보들
		candidates = new ArrayList<int[]>();
		candidateLen = 0;
		
		// 바이러스 위치
		virus = new ArrayList<int[]>();
		
		// 지도
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					candidates.add(new int[] {i, j});
					candidateLen++;
				} else if (map[i][j] == 2) {
					virus.add(new int[] {i, j});
				}
			}
		}
		
		// 조합 위한 방문 배열 및 조합 배열
		visited = new boolean[candidateLen];
		arr = new int[3];
		
		// 정답
		maxAns = 0;
		
		// 조합 돌리면서 계산
		combi(0, 0);
		
		// 정답 출력
		System.out.println(maxAns);
	}
}