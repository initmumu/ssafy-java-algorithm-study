import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ16236 {
	
	public static int N;
	public static int [][] graph;
	public static int current_size = 2;
	public static int current_x = 0;
	public static int current_y = 0;
	public static int [] dx = {-1, 0, 1, 0};
	public static int [] dy = {0, 1, 0, -1};
	
	
//	모든 위치까지의 "최단 거리만" 계산하는 BFS 함수
	public static int[][] bfs() {
//		값이 -1이라면 도달할 수 없다는 의미(초기화)
		int [][] dist = new int [N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dist[i][j] = -1;
			}
		}
		
//		시작 위치는 도달이 가능하다고 보며 거리는 0
		Deque<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {current_x, current_y});
		dist[current_x][current_y] = 0;
		while(!queue.isEmpty()) {
			int [] first = queue.pollFirst();
			int x = first[0];
			int y = first[1];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (0 <= nx && nx < N && 0 <= ny && ny < N) {
//					자신의 크기보다 작거나 같은 경우에 지나갈 수 있음
					if (dist[nx][ny] == -1 && graph[nx][ny] <= current_size) {
						dist[nx][ny] = dist[x][y] + 1;
						queue.add(new int [] {nx, ny});
					}
				}
			}
		}
//		모든 위치까지의 최단 거리 테이블 반환 
		return dist;
	}
	
//	최단 거리 테이블이 주어졌을 때, 먹을 물고기를 찾는 함수
	public static int[] find(int[][] dist) {
		int x = 0;
		int y = 0;
		int min_dist = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
//				도달이 가능하면서 먹을 수 있는 물고기일 때
				if (dist[i][j] != -1 && 1 <= graph[i][j] && graph[i][j] < current_size) {
//					가장 가까운 물고기 1마리만 선택
					if (dist[i][j] < min_dist) {
						x = i;
						y = j;
						min_dist = dist[i][j];
					}
				}
			}
		}

		if (min_dist == Integer.MAX_VALUE) { //		먹을 수 있는 물고기가 없는 경우
			return new int [] {};
		} else {                             // 	먹을 물고기의 위치와 최단 거리
			return new int [] {x, y, min_dist};
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		graph = new int [N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		아기 상어의 시작 위치를 찾은 뒤에 그 위치엔 아무것도 없다고 처리
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (graph[i][j] == 9) {
					current_x = i;
					current_y = j;
					graph[i][j] = 0;
				}
			}
		}
		int result = 0;
		int ate = 0;
		while (true) {
//			먹을 수 있는 물고기의 위치 찾기
			int [] value = find(bfs());
//			먹을 수 있는 물고기가 없는 경우, 현재까지 움직인 거리 출력
			if (value.length == 0) {
				System.out.println(result);
				break;
			} else {
//				현재 위치 갱신 및 이동 거리 변경
				current_x = value[0];
				current_y = value[1];
				result += value[2];
//				먹은 위치에는 이제 아무것도 없도록 처리
				graph[current_x][current_y] = 0;
				ate += 1;
//				자신의 현재 크기 이상으로 먹은 경우, 크기 증가
				if (ate >= current_size) {
					current_size += 1;
					ate = 0;
				}
			}
		}
		

	}

}
