    import java.io.*;
    import java.util.*;

    public class BOJ15666 {

        static int N, M;
        static int[] arr;
        static List<Integer> seq;
        static StringBuilder sb = new StringBuilder();

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());


            arr = new int[M];

            Set<Integer> set = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                set.add(Integer.parseInt(st.nextToken()));
            }

            seq = new ArrayList<>(set);
            N = seq.size();

            Collections.sort(seq);

            DFS(0, 0);
            System.out.println(sb);
        }

        static void DFS(int start, int depth) {
            if (depth == M) {
                for (int a: arr) {
                    sb.append(a).append(" ");
                }
                sb.append("\n");
                return;
            }

            for (int i = start; i < N; i++) {
                arr[depth] = seq.get(i);
                DFS(i, depth + 1);
            }
        }
    }
