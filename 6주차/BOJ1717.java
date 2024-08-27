import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];

        for(int i = 0; i <= n; i++){
            arr[i] = i;
        }

        int ins, a, b;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            ins = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if(ins == 0){
                int A = DFS(a);
                int B = DFS(b);
                if(A != B) arr[A] = b;
            }

            if(ins == 1){
                if (DFS(a) == DFS(b)) {
                    sb.append("YES").append('\n');
                } else {
                    sb.append("NO").append('\n');
                }
            }
        }

        System.out.print(sb);
    }

    static int DFS(int x){
        if(arr[x] != x){
            arr[x] = DFS(arr[x]);
        }
        return arr[x];
    }
}