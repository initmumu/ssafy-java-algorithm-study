import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] data = new int[N][N];
        
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				data[i][j] = str.charAt(j) - '0';
			}
		}
		
		QuadTree(data, 0, 0, N);
	}
	
	// 영상 데이터를 4등분으로 나눔
	static void QuadTree(int[][] data, int i, int j, int size) {
		// 영상 데이터가 모두 같은 숫자면 압축
		if (isUniform(data, i, j, size)) {
			if (data[i][j] == 0) 
				System.out.print(0);
			else 
				System.out.print(1);
			return;
		}

		int newSize = size / 2;
		
		// 재귀 시작전에는 "(" 재귀 끝나면 ")"
		System.out.print("(");
		QuadTree(data, i, j, newSize);
		QuadTree(data, i, j + newSize, newSize);
		QuadTree(data, i + newSize, j, newSize);
		QuadTree(data, i + newSize, j + newSize, newSize);
		System.out.print(")");
	}
	
	// 모든 칸이 같은 숫자인지 체크
	static boolean isUniform(int[][] data, int i, int j, int size) {
		int value = data[i][j];
		
		for (int a = i; a < i + size; a++) {
			for (int b = j; b < j + size; b++) {
				if (data[a][b] != value)
					return false;
			}
		}
		return true;
	}
}
