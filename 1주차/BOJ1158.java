import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> q = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            q.add(i);
        }

        System.out.print("<");
        int t = 0;

        for(int i = 0; i < N - 1; i++) {
            t = (t + M - 1) % q.size();
            System.out.print(q.remove(t) + ", ");
        }

        System.out.print(q.remove(0)+">");
    }
}
