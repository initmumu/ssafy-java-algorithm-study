import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2573 {
	
	public static int N, M;
	public static int graph [][];
	public static boolean visited [][];
	public static int dy [] = {-1, -1, -1, 0, 0, 1, 1, 1};
	public static int dx [] = {-1, 0, 1, -1, 1, -1, 0, 1};
	public static int dy2 [] = {-1, 0, 1, 0};
	public static int dx2 [] = {0, 1, 0, -1};
	public static List<int[]> sumGraph;
	
	public static int meltingBingsan() {
		visited = new boolean [N][M];
		int bingsan = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j]) continue;
				if (graph[i][j] < 1) continue;
				visited[i][j] = true;
				dfs(i, j);
				bingsan++;
			}
		}
		
		return bingsan;
	}
	
	public static boolean checkBound(int ny, int nx) {
		return ny < 0 || ny >= N || nx < 0 || nx >= M;
	}
	
	public static void dfs(int y, int x) {
		
		decreaseBingsan(y, x);
		
		for (int i = 0; i < 4; i++) {
			int ny = y + dy2[i];
			int nx = x + dx2[i];
			if (checkBound(ny, nx)) continue;
			if (visited[ny][nx]) continue;
			if (graph[ny][nx] < 1) continue;
			visited[ny][nx] = true;
			dfs(ny, nx);
			
		}
	}

	public static void decreaseBingsan(int y, int x) {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy2[i];
			int nx = x + dx2[i];
			if (checkBound(ny, nx)) continue;
			if (graph[ny][nx] > 0) continue;
			sum++;
		}
		sumGraph.add(new int [] {y, x, sum});
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int [N][M];
		
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		int seperatedBingsan = 1;
		while (seperatedBingsan == 1) {
			sumGraph = new ArrayList<>();
			seperatedBingsan = meltingBingsan();
			if (seperatedBingsan >= 2) {
				break;
			} else if (seperatedBingsan == 0) {
				result = 0;
				break;
			}
			
			for (int i = 0; i < sumGraph.size(); i++) {
				int sumList [] = sumGraph.get(i);
				int y = sumList[0];
				int x = sumList[1];
				int sum = sumList[2];
				graph[y][x] -= sum;
			}
			result++;
		}
		System.out.println(result);
	}

}
