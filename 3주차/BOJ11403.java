import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;


public class Main {
    static int N;
    static int[][] graph;
    static boolean[] visited;

    public static void DFS(int n) {
        if(visited[n]) return;
        visited[n] = true;
        for(int i = 0; i < N; i++) {
            if(graph[n][i] == 1) {
                DFS(i);
                for(int j = 0; j < N; j++) {
                    graph[n][j] = graph[i][j] | graph[n][j];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = readInt();

        graph = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++){
                graph[i][j] = readInt();
            }
        }

        for(int i = 0; i < N; i++) {
            visited = new boolean[N];
            DFS(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(graph[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    public static int readInt() throws IOException {
        int input;
        int result = 0;
        while((input = System.in.read()) > 47) {
            result = (result<<3) + (result<<1) + (input & 15);
        }
        return result;
    }

}
