import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][] turnInfo;
    static boolean[] visited;
    static int min;
    static int N;
    static int M;
    static int K;

    // 배열 돌리기
    public static void TurnArray(int r, int c, int s) {
        r--;
        c--;
        int sY = r - s;
        int sX = c - s;
        int eY = r + s;
        int eX = c + s;
        int len = eX - sX;

        if(sX >= eX) return;

        int temp = map[sY][sX];

        for(int i = 0; i < len; i++) {
            map[sY + i][sX] = map[sY + i + 1][sX];
        }

        for(int i = 0; i < len; i++) {
            map[eY][sX + i] = map[eY][sX + i + 1];
        }

        for(int i = 0; i < len; i++) {
            map[eY - i][eX] = map[eY - i - 1][eX];
        }

        for(int i = 0; i < len; i++) {
            map[sY][eX - i] = map[sY][eX - i - 1];
        }

        map[sY][sX + 1] = temp;

        TurnArray(r + 1, c + 1, s - 1);
    }

    // 배열 역방향 돌리기
    public static void ReverseTurnArray(int r, int c, int s) {
        r--;
        c--;
        int sY = r - s; //2 - 2 = 0
        int sX = c - s; //3 - 2 = 1
        int eY = r + s; //3 + 2 = 5
        int eX = c + s;
        int len = eX - sX; //5 - 1 = 4

        if(sX >= eX) return;

        int temp = map[sY][sX];

        for(int i = 0; i < len; i++) {
            map[sY][sX + i] = map[sY][sX + i + 1];
        }

        for(int i = 0; i < len; i++) {
            map[sY + i][eX] = map[sY + i + 1][eX];
        }

        for(int i = 0; i < len; i++) {
            map[eY][eX - i] = map[eY][eX - i - 1];
        }

        for(int i = 0; i < len; i++) {
            map[eY - i][sX] = map[eY - i - 1][sX];
        }

        map[sY + 1][sX] = temp;

        ReverseTurnArray(r + 1, c + 1, s - 1);
    }

    public static void DFS(int count) {
        // K 번 돌렸을 경우
        if(count == K) {
            // 배열 A 계산 후 min 갱신
            int minN = Integer.MAX_VALUE, tempN;
            for(int y = 0; y < N; y++) {
                tempN = 0;
                for(int x = 0; x < M; x++) {
                    tempN += map[y][x];
                }
                minN = Math.min(minN, tempN);
            }
            min = Math.min(minN, min);
            return;
        }

        // Swap 순열
        for(int i = count; i < K; i++) {
            Swap(count, i);
            // 배열 돌리기
            TurnArray(turnInfo[count][0], turnInfo[count][1], turnInfo[count][2]);
            DFS(count + 1);
            // 배열 원상 복구
            ReverseTurnArray(turnInfo[count][0], turnInfo[count][1], turnInfo[count][2]);
            Swap(count, i);
        }
    }

    public static void Swap(int n1, int n2) {
        int[] temp = turnInfo[n1];
        turnInfo[n1] = turnInfo[n2];
        turnInfo[n2] = temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        turnInfo = new int[K][3];
        visited = new boolean[K];
        min = Integer.MAX_VALUE;
        for(int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                turnInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0);
        System.out.println(min);
    }

}
