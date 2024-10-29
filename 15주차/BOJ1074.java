import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074 {
	public static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int size = (int) Math.pow(2, N);
		result = 0;
		dfs(size, r, c);
		System.out.println(result);
	}

	public static void dfs(int size, int y, int x) {
		if(size == 1)
			return;
		
		if(y < size/2 && x < size/2) {
			dfs(size/2, y, x);
		}
		else if(y < size/2 && x >= size/2) {
			result += size * size / 4;
			dfs(size/2, y, x - size/2);
		}
		else if(y >= size/2 && x < size/2) {
			result += (size * size / 4) * 2;
			dfs(size/2, y - size/2, x);
		}
		else {
			result += (size * size / 4) * 3;
			dfs(size/2, y - size/2, x - size/2);
		}
	}
}
