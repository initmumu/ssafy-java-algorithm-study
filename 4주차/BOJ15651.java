import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static StringBuilder sb;
    static int[] readBuff;

    public static void DFS(int n, int depth){
        if(depth == M) {
            readBuff[depth - 1] = n;
            sbInput();
            return;
        }

        for(int i = 1; i <= N; i++){
            readBuff[depth - 1] = n;
            DFS(i, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        readBuff = new int[M];

        for(int i = 1; i <= N; i++){
            DFS(i, 1);
        }

        System.out.print(sb);
    }

    public static void sbInput(){
        for(int n : readBuff){
            sb.append(n).append(' ');
        }
        sb.append('\n');
    }
}
