import java.io.*;
import java.util.*;

public class BOJ2644 {

    static int n;
    static List<Integer>[] people;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        people = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            people[i] = new ArrayList<>();
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            people[x].add(y);
            people[y].add(x);
        }

        System.out.println(BFS(start, end));
    }

    static int BFS(int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;
        queue.add(start);

        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int cur = queue.poll();

                for (int person : people[cur]) {
                    if (person == end) {
                        return cnt + 1;
                    }

                    if (visited[person]) {
                        continue;
                    }

                    visited[person] = true;
                    queue.add(person);
                }
            }

            cnt++;
        }

        return -1;
    }
}
