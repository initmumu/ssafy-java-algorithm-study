import java.util.*;

public class BOJ1525 {

    static String target = "123456780";

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(sc.nextInt());
            }
        }

        System.out.println(BFS(sb.toString()));
    }

    public static int BFS(String start) {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(start);

        Set<String> visited = new HashSet<>();
        visited.add(start);

        int moveCnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String cur = queue.poll();

                if (cur.equals(target)) {
                    return moveCnt;
                }

                int zeroIndex = cur.indexOf('0');
                int x = zeroIndex / 3;
                int y = zeroIndex % 3;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3) {
                        continue;
                    }

                    String next = swap(cur, zeroIndex, nx * 3 + ny);

                    if (!visited.contains(next)) {
                        queue.add(next);
                        visited.add(next);
                    }
                }
            }

            moveCnt++;
        }

        return -1;
    }

    static String swap(String cur, int x, int y) {
        char[] charArray = cur.toCharArray();
        char temp = charArray[x];
        charArray[x] = charArray[y];
        charArray[y] = temp;
        return new String(charArray);
    }
}
