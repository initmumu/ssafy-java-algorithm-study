import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070 {
	
	public static int N;
	public static int [][] graph;
	public static int result;
	
	public static void dfs(int x, int y, int state) {
		if (x >= N || y >= N) return;
		if (graph[x][y] == 1) return;
		
		
		if (x == N-1 && y == N-1) {
			result++;
			return;
		}
		
        if (state == 0) {
            if (y + 1 < N && graph[x][y + 1] == 0) {
                dfs(x, y + 1, 0);
            }
            if (x + 1 < N && y + 1 < N && graph[x + 1][y] == 0 && graph[x][y + 1] == 0 && graph[x + 1][y + 1] == 0) {
                dfs(x + 1, y + 1, 1);
            }
        } else if (state == 1) {
            if (y + 1 < N && graph[x][y + 1] == 0) {
                dfs(x, y + 1, 0);
            }
            if (x + 1 < N && y + 1 < N && graph[x + 1][y] == 0 && graph[x][y + 1] == 0 && graph[x + 1][y + 1] == 0) {
                dfs(x + 1, y + 1, 1);
            }
            if (x + 1 < N && graph[x + 1][y] == 0) {
                dfs(x + 1, y, 2);
            }
        } else if (state == 2) {
            if (x + 1 < N && graph[x + 1][y] == 0) {
                dfs(x + 1, y, 2);
            }
            if (x + 1 < N && y + 1 < N && graph[x + 1][y] == 0 && graph[x][y + 1] == 0 && graph[x + 1][y + 1] == 0) {
                dfs(x + 1, y + 1, 1);
            }
        }
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		graph = new int [N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = 0;
		
		dfs(0, 1, 0);
		System.out.println(result);
	}

}
