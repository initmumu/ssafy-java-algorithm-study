import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ20056 {
	
	public static int N, M, K;
	public static int graph[][];
	public static int mass[][];
	public static int direction[][];
	public static int speed[][];
	public static int [] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static int [] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static int calc(int num) {
		if (num % N == 0) {
			return N;
		}
		return num % N;
	}
	
	public static void move() {
//		임시 저장 배열들
		int graph_temp[][] = new int[N+1][N+1];
		int mass_temp[][] = new int[N+1][N+1];
		int direction_temp[][] = new int[N+1][N+1];
		int speed_temp[][] = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (graph[i][j] == 4) {
					System.out.println("호출");
					int d = direction[i][j];
					System.out.println(d);
					int s = speed[i][j];
					System.out.println(s);
					while (d < 8) {
						int nx = calc((N + i + dx[d] * (s % N)) % N); 
						int ny = calc((N + j + dy[d] * (s % N)) % N); 
						System.out.println("nx: " + nx + ", ny: " + ny);
						graph_temp[nx][ny] += graph[i][j] / 4;
						mass_temp[nx][ny] += mass[i][j];
						direction_temp[nx][ny] += direction[i][j];
						speed_temp[nx][ny] += speed[i][j];
						d += 2;
					}
//					graph[i][j] = 0;
//					mass[i][j] = 0;
//					direction[i][j] = 0;
//					speed[i][j] = 0;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
//				if (graph[i][j] == 4) {
//					System.out.println("호출");
//					int d = direction[i][j];
//					int s = speed[i][j];
//					for (int k = 0; k < 4; k +=2) {
//						d += k;
//						int nx = calc((N + i + dx[d] * (s % N)) % N); 
//						int ny = calc((N + j + dy[d] * (s % N)) % N); 					
//						graph_temp[nx][ny] += graph[i][j];
//						mass_temp[nx][ny] += mass[i][j];
//						direction_temp[nx][ny] += direction[i][j];
//						speed_temp[nx][ny] += speed[i][j];
//					}
//					graph[i][j] = 0;
//					continue;
//				}
				if (graph[i][j] > 0) {
					int d = direction[i][j];
					int s = speed[i][j];
					int nx = calc((N + i + dx[d] * (s % N)) % N); 
					int ny = calc((N + j + dy[d] * (s % N)) % N); 					
					graph_temp[nx][ny] += graph[i][j];
					mass_temp[nx][ny] += mass[i][j];
					direction_temp[nx][ny] += direction[i][j];
					speed_temp[nx][ny] += speed[i][j];
				}
			}
		}
		
		
//		임시 저장한 배열들 복구
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {				
				graph[i][j] = graph_temp[i][j];
				mass[i][j] = mass_temp[i][j];
				direction[i][j] = direction_temp[i][j];
				speed[i][j] = speed_temp[i][j];
			}
		}
	}
	
	public static void fireballTwice() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (graph[i][j] >= 2) {
//					질량은 합쳐진 파이어볼 질량의 합 / 5
					mass[i][j] = (mass[i][j] / 5);
//					속력은 합쳐진 파이어볼 속력의 합 / 합쳐진 파이어볼의 개수
					speed[i][j] = speed[i][j] / graph[i][j];
					
					if (direction[i][j] % graph[i][j] == 0) {
						// 모두 짝수 또는 모두 홀수
						direction[i][j] = 0;
					} else {
						direction[i][j] = 1;
					}
					graph[i][j] = 4;
				}
			}
		}
	}
	public static void massZero() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (mass[i][j] == 0) {
					graph[i][j] = 0;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		graph = new int [N+1][N+1];
		mass = new int [N+1][N+1];
		direction = new int [N+1][N+1];
		speed = new int [N+1][N+1];
		for (int i = 0; i < M; i++) {
//			처음 모든 파이어볼들의 위치는 다 다른지?? 일단 스킵
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			graph[r][c] += 1;
			mass[r][c] = m;
			direction[r][c] = d;
			speed[r][c] = s;
		}
		System.out.println(Arrays.deepToString(graph));
		for (int i = 0; i < K; i++) {
//			d 방향, s 속력 만큼 이동
			move();
			System.out.println(Arrays.deepToString(graph));
//			2개 이상 파이어볼 작업
			fireballTwice();
			
//			질량 0인 파이어볼 삭제
			massZero();
			System.out.println(Arrays.deepToString(graph));
		}
		int result = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (graph[i][j] > 0) {
					result += mass[i][j];
				}
			}
		}
		System.out.println(result);
	}

}
