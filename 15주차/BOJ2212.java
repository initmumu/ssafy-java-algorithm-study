import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ2212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        
        int[] graph = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            graph[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(graph);
        
        int[] dist = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            dist[i] = graph[i + 1] - graph[i];
        }
        
        Arrays.sort(dist);
        
        int result = 0;
        for (int i = 0; i < N - K; i++) {
            result += dist[i];
        }
        
        System.out.println(result);
    }
}
