import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class BOJ7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int i = 0; i < k; i++) {
                String[] command = br.readLine().split(" ");
                String operator = command[0];
                int value = Integer.parseInt(command[1]);

                // 삭제 연산
                if (operator.equals("D")) {
                    if (map.isEmpty()) {
                        continue;
                    }

                    int remove = map.lastKey();
                    if (value == -1) {
                        remove = map.firstKey();
                    }

                    if (map.get(remove) == 1) {
                        map.remove(remove);
                    }
                    else {
                        map.put(remove, map.get(remove) - 1);
                    }
                }
                // 삽입 연산
                if (operator.equals("I")) {
                    map.put(value, map.getOrDefault(value, 0) + 1);
                }
            }
            if (map.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
            }
        }

        System.out.print(sb);
    }
}
