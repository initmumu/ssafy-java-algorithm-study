import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ21610 {
	
	public static int N, M;
	public static int[][] graph;
	public static int[][] cloud;
	public static final int[] dx = {-1, -1, 1, 1};
	public static final int[] dy = {-1, 1, -1, 1};
	public static int[] nx;
	public static int[] ny;
	
	
	public static int calcMinus(int num) {
		if (num > N) {
			return num % N;
		}
		return num;
	}
	
	public static int diag(int i, int j) {
		int count = 0;
//		System.out.println("i= " + i + ", j= " + j);
		for (int k = 0; k < 4; k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];
			if (nx > 0 && nx <= N && ny > 0 && ny <= N) {
				if (graph[nx][ny] > 0) {
					count++;
				}
			}
		}
//		System.out.println(count);
		return count;
	}
	
	public static void FirstCloudMove(int d, int s) {
		nx = new int[]{N-1, N-1, N, N};
		ny = new int[]{1, 2, 1, 2};
		int start_x = N-1;
		int start_y = 1;
		switch(d) {
			case 1:
//				start_y = Math.abs(start_y + calc(s, N)) + 1;
				for (int k = 0; k < 4; k++) {
//					ny[k] = ny[k] + calc(Math.abs(N-calc(s,N)), N);
					ny[k] = calcMinus(ny[k] = N + (ny[k] - ((s % N))));
				}
				break;
			case 2:
//				start_x = Math.abs(start_x - calc(s, N)) + 1;
//				start_y = Math.abs(start_y - calc(s, N)) + 1;
				break;
			case 3:
				for (int k = 0; k < 4; k++) {
//					ny[k] = ny[k] + calc(Math.abs(N-calc(s,N)), N);
					ny[k] = (ny[k] + (s % N) + 1) % N;
				}
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			
		}
		
		System.out.println("nx= " + Arrays.toString(nx) + ", ny= " + Arrays.toString(ny));
//		System.out.println("start_x= " + start_x + ", start_y= " + start_y);
		for (int i = start_x; i <= start_x + 1; i++) {
			for (int j = start_y; j <= start_y + 1; j++) {
				graph[i][j]++;
				
			}
		}
		for (int i = start_x; i <= start_x + 1; i++) {
			for (int j = start_y; j <= start_y + 1; j++) {
				graph[i][j] += diag(i, j);
			}
		}
		
//		첫 번째 구름을 제외한 나머지 2이상 이면 구름 체크 및 물 2 감소
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if ((i == start_x && j == start_y) || (i == start_x && j == start_y + 1) 
						|| (i == start_x + 1 && j == start_y) || (i == start_x + 1 && j == start_y + 1) ) {
					continue;
				}
				if (graph[i][j] >= 2) {
					cloud[i][j] = 1;
					graph[i][j] -= 2;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int [N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cloud = new int [N+1][N+1];

		st = new StringTokenizer(br.readLine());
		int d = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		FirstCloudMove(d, s);
		System.out.println(Arrays.deepToString(graph));
		System.out.println(Arrays.deepToString(cloud));
		for (int i = 2; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
		}

	}

}
