import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[][] space;
	static boolean[][] visited;
	static boolean[][] visited2;
	static int N;
	
	public static void DFS(int x, int y, char c) {
		if(isOutOfMap(x, y) || visited[y][x] || space[y][x] != c) return;
		
		visited[y][x] = true;
		
		DFS(x + 1, y, c);
		DFS(x - 1, y, c);
		DFS(x, y + 1, c);
		DFS(x, y - 1, c);
	}
	
	public static void DFS2(int x, int y, char c) {
		if(isOutOfMap(x, y) || visited2[y][x]) return;
		if(space[y][x] == c || 
				(space[y][x] == 'G' && c == 'R') || 
				(space[y][x] == 'R' && c == 'G')) {
			visited2[y][x] = true;
			
			DFS2(x + 1, y, c);
			DFS2(x - 1, y, c);
			DFS2(x, y + 1, c);
			DFS2(x, y - 1, c);
		}
	}
	
	public static boolean isOutOfMap(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		int groupCount = 0;
		int groupCount2 = 0;
		space = new char[N][];
		visited = new boolean[N][N];
		visited2 = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			space[i] = bf.readLine().toCharArray();
		}
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if(visited[y][x]) continue;
				groupCount++;
				DFS(x, y, space[y][x]);
			}
		}
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if(visited2[y][x]) continue;
				groupCount2++;
				DFS2(x, y, space[y][x]);
			}
		}
		
		System.out.printf("%d %d", groupCount, groupCount2);
	}

}
