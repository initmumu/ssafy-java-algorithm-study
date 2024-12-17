import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ20056 {

    public static class FireBall {

        int r;
        int c;
        int m;
        int s;
        int d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static int N, M, K;
    static List<FireBall>[][] graph;
    static List<FireBall> fireballs = new ArrayList<>();
    public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};


    public static int calc(int num) {
        if (num % N == 0) {
            return N;
        }
        return num % N;
    }

    public static void move() {
        for (FireBall ball : fireballs) {
            int nx = calc((N + ball.r + dx[ball.d] * (ball.s % N)) % N);
            int ny = calc((N + ball.c + dy[ball.d] * (ball.s % N)) % N);
            ball.r = nx;
            ball.c = ny;
            graph[nx][ny].add(ball);
        }
    }

    public static void fireballTwice() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (graph[i][j].size() <= 1) {
                    graph[i][j].clear();
                    continue;
                }
                int massSum = 0;
                int speedSum = 0;
                boolean even = graph[i][j].get(0).d % 2 == 0 ? true : false;
                boolean odd = graph[i][j].get(0).d % 2 == 1 ? true : false;
                for (FireBall f : graph[i][j]) {
                    massSum += f.m;
                    speedSum += f.s;
                    even = even & f.d % 2 == 0 ? true : false;
                    odd = odd & f.d % 2 == 1 ? true : false;
                    fireballs.remove(f);
                }
                int nMass = massSum / 5;
                int size = graph[i][j].size();
                graph[i][j].clear();
                int nSpeed = speedSum / size;

                if (nMass == 0) {
                    continue;
                }

                if (even || odd) { // 0 2 4 6
                    for (int k = 0; k < 8; k += 2) {
                        fireballs.add(new FireBall(i, j, nMass, nSpeed, k));
                    }
                } else {
                    for (int k = 1; k < 8; k += 2) {
                        fireballs.add(new FireBall(i, j, nMass, nSpeed, k));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                graph[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireballs.add(new FireBall(r, c, m, s, d));
        }

        for (int i = 0; i < K; i++) {
//			d 방향, s 속력 만큼 이동
            move();

//			2개 이상 파이어볼 작업 , 질량 0인 파이어볼 삭제
            fireballTwice();

        }
        int result = 0;
        for (FireBall f : fireballs) {
            result += f.m;
        }
        System.out.println(result);
    }

}
