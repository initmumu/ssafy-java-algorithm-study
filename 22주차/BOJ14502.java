import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14502 {

    static int N, M;
    static int[][] map;
    static int maxSafeArea = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        // 빈 칸 저장하기 위한 List
        List<int[]> emptySpaces = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    emptySpaces.add(new int[]{i, j});
                }
            }
        }

        for (int i = 0; i < emptySpaces.size(); i++) {
            for (int j = i + 1; j < emptySpaces.size(); j++) {
                for (int k = j + 1; k < emptySpaces.size(); k++) {
                    int[][] tempMap = new int[N][M];
                    for (int a = 0; a < N; a++) {
                        tempMap[a] = map[a].clone();
                    }

                    tempMap[emptySpaces.get(i)[0]][emptySpaces.get(i)[1]] = 1;
                    tempMap[emptySpaces.get(j)[0]][emptySpaces.get(j)[1]] = 1;
                    tempMap[emptySpaces.get(k)[0]][emptySpaces.get(k)[1]] = 1;

                    int safeArea = spreadVirus(tempMap);
                    maxSafeArea = Math.max(maxSafeArea, safeArea);
                }
            }
        }

        System.out.println(maxSafeArea);
    }

    static int spreadVirus(int[][] laboratoryMap) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();

        // 초기 바이러스 위치 Queue에 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (laboratoryMap[i][j] == 2) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        // 바이러스 퍼트리기
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (visited[nx][ny] || laboratoryMap[nx][ny] == 1 || laboratoryMap[nx][ny] == 2) {
                    continue;
                }

                visited[nx][ny] = true;
                laboratoryMap[nx][ny] = 2;
                queue.add(new int[]{nx, ny});
            }
        }

        // 안전영역 크기계산
        int safeArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (laboratoryMap[i][j] == 0) {
                    safeArea++;
                }
            }
        }

        return safeArea;
    }
}
