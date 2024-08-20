import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16637 {
	
	public static int N;
	public static int[] graph;
	public static char[] oper;
	public static int MAX_VALUE;
	
	public static int calc(int a, int b, char operator) {
		if (operator == '+') return a+b;
		else if (operator == '-') return a-b;
		else return a*b;
	}
	
	public static void dfs(int depth, int total) {
		if (depth == graph.length-1) {
			MAX_VALUE = Math.max(MAX_VALUE, total);
			return;
		}
		
//		괄호 치고 연산
		int cal = calc(total, graph[depth + 1], oper[depth]);
		dfs(depth+1, cal);
//		괄호 안치고 연산
		if (depth + 2 <= graph.length-1) {
			cal = calc(total, calc(graph[depth + 1], graph[depth + 2], oper[depth + 1]), oper[depth]);
			dfs(depth + 2, cal);
		}
		
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N/2+1];
		oper = new char[N/2];
		String s = br.readLine();
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) graph[i/2] = s.charAt(i)-48;
			else oper[i/2] = s.charAt(i);
		}
		MAX_VALUE = Integer.MIN_VALUE;
		dfs(0, graph[0]);
		System.out.println(MAX_VALUE);
	}

}
