import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] parent;
    static List<Integer>[] parties;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        parties = new ArrayList[M];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int knowCount = Integer.parseInt(st.nextToken());
        List<Integer> mola = new ArrayList<>();
        for (int i = 0; i < knowCount; i++) {
            mola.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            parties[i] = new ArrayList<>();

            int count = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());
            parties[i].add(prev);
            if (count <= 1)
                continue;
            for (int j = 1; j < count; j++) {
                int cur = Integer.parseInt(st.nextToken());
                parties[i].add(cur);
                Merge(prev, cur);
                prev = cur;
            }
        }

        Set<Integer> nono = new HashSet<>();
        for (int n : mola) {
            nono.add(Find(n));
        }
        int result = 0;

        for (List<Integer> party : parties) {
            boolean anything = false;
            for (int n : party) {
                for (int n2 : nono) {
                    if (Find(n) == Find(n2)) {
                        anything = true;
                        break;
                    }
                }
            }

            if (!anything)
                result++;
        }

        System.out.print(result);
    }

    public static int Find(int n) {
        if (parent[n] != n)
            n = Find(parent[n]);
        return n;
    }

    public static void Merge(int a, int b) {
        int aP = Find(a);
        int bP = Find(b);
        if (aP == bP)
            return;
        parent[bP] = aP;
    }
}
