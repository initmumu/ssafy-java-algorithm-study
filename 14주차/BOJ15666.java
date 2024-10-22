import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ15666 {
    static int N, M;
    static int[] graph;
    static List<List<Integer>> result = new ArrayList<>();

    public static void dfs(int n, int s, List<Integer> arr) {
        if (n == M) {
            result.add(new ArrayList<>(arr));
            return;
        }

        for (int j = s; j < N; j++) {
            arr.add(graph[j]);
            dfs(n + 1, j, arr);
            arr.remove(arr.size() - 1); // backtrack
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        graph = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            graph[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(graph);
        dfs(0, 0, new ArrayList<>());

        Set<List<Integer>> set = new HashSet<>();
        List<List<Integer>> set_list = new ArrayList<>();

        for (List<Integer> item : result) {
            if (!set.contains(item)) {
                set.add(item);
                set_list.add(item);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (List<Integer> item : set_list) {
            for (int num : item) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.print(sb.toString());
    }
}
