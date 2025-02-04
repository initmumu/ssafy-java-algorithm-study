import java.io.*;
import java.util.StringTokenizer;

public class BOJ10830 {
    static int n;
    static final int MOD = 1000;
    
    public static int[][] multiply(int[][] A, int[][] B) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += (long) A[i][k] * B[k][j];
                }
                result[i][j] = (int)(sum % MOD);
            }
        }
        return result;
    }
    
    public static int[][] dfs(int[][] graph, long exp) {
        if (exp == 1) {
            return graph;
        }
        int[][] temp = dfs(graph, exp / 2);
        temp = multiply(temp, temp);
        if (exp % 2 == 1) {
            temp = multiply(temp, graph);
        }
        return temp;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken()) % MOD;
            }
        }
        
        int[][] result = dfs(graph, b);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
