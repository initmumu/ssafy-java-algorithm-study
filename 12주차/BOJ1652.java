import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[] biyong;
    static int[] biyong2;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N];
        for(int i = 0; i < N; i++){
            parent[i] = i;
        }

        biyong = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            biyong[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            merge(a - 1, b - 1);
        }

        int result = 0;
        for(int i = 0; i < N; i++){
            if(parent[i] == i){
                result += biyong[i];
            }
        }

        if(result > K){
            System.out.print("Oh no");
        } else {
            System.out.print(result);
        }
    }

    public static int find(int n){
        if(parent[n] == n){
            return n;
        }
        return parent[n] = find(parent[n]);
    }

    public static void merge(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y) return;
        parent[y] = x;
        biyong[x] = Math.min(biyong[y], biyong[x]);
    }
}
