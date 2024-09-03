import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA1230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 10; test_case++) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<String> graph = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                graph.add(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            ArrayList<String> commands = new ArrayList<>();
            while (st.hasMoreTokens()) {
                commands.add(st.nextToken());
            }

            int i = 0;
            while (i < commands.size()) {
                String commandType = commands.get(i);

                if ("I".equals(commandType)) {
                    int y = Integer.parseInt(commands.get(i + 1));
                    int s = Integer.parseInt(commands.get(i + 2));
                    for (int j = i + 3 + s - 1; j >= i + 3; j--) {
                        graph.add(y, commands.get(j));
                    }
                    i += 3 + s;
                } else if ("A".equals(commandType)) {
                    int s = Integer.parseInt(commands.get(i + 1));
                    for (int j = i + 2; j < i + 2 + s; j++) {
                        graph.add(commands.get(j));
                    }
                    i += 2 + s;
                } else if ("D".equals(commandType)) {
                    int x = Integer.parseInt(commands.get(i + 1));
                    int y = Integer.parseInt(commands.get(i + 2));
                    for (int j = 0; j < y; j++) {
                        graph.remove(x);
                    }
                    i += 3;
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(test_case).append(" ");
            for (int k = 0; k < 10; k++) {
                sb.append(graph.get(k)).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }
}
