import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;

    public static void DFS(int n, int depth, StringBuilder sb){
        StringBuilder sb2 = new StringBuilder();
        sb2.append(sb);

        if(depth == M) {
            sb2.append(n);
            System.out.println(sb2);
            return;
        }

        sb2.append(n).append(' ');

        for(int i = n + 1; i <= N - (M - depth - 1); i++){
            DFS(i, depth + 1, sb2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= N - (M - 1); i++){
            DFS(i, 1, sb);
        }
    }
}
