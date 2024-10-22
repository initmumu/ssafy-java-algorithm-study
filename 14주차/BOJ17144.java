import java.io.*;
import java.util.*;

public class BOJ17144 {

    static int R, C, T;
    static int[][] map;
    static int airPurifier1, airPurifier2;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 공기청정기의 위치 변수(airPurifier1, airPurifier2) 초기화
        initAirPurifierPos();

        // T초 동안 시뮬레이션 반복
        for (int i = 0; i < T; i++) {
            spreadDust();
            OperateAirPurifier();
        }

        // 남아있는 미세먼지 양 출력
        System.out.println(getRemainingDust());
    }

    // 공기청정기의 위치를 찾아 변수에 할당
    static void initAirPurifierPos() {
        for (int i = 0; i < R; i++) {
            if (map[i][0] == -1) {
                airPurifier1 = i;
                airPurifier2 = i + 1;
                return;
            }
        }
    }
    
    // 미세먼지를 확산시킴
    static void spreadDust() {
        int[][] tempMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    int spreadAmount = map[i][j] / 5;   // 4방향으로 확산되는 미세먼지의 양
                    int totalSpreadAmount = 0;          // 4방향으로 확산되는 미세먼지의 양의 합

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                            continue;
                        }
                        if (map[nx][ny] == -1) {
                            continue;
                        }

                        tempMap[nx][ny] += spreadAmount;
                        totalSpreadAmount += spreadAmount;
                    }

                    tempMap[i][j] += map[i][j] - totalSpreadAmount;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            map[i] = tempMap[i].clone();
        }

        map[airPurifier1][0] = -1;
        map[airPurifier2][0] = -1;
    }
    
    // 공기청정기가 작동
    static void OperateAirPurifier() {
        // 위쪽 공기청정기 (반시계 방향)
        for (int i = airPurifier1 - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        for (int i = 0; i < airPurifier1; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            map[airPurifier1][i] = map[airPurifier1][i - 1];
        }

        map[airPurifier1][1] = 0;  // 공기청정기로 들어간 미세먼지 제거
        
        // 아래쪽 공기청정기 (시계 방향)
        for (int i = airPurifier2 + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }
        for (int i = R - 1; i > airPurifier2; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            map[airPurifier2][i] = map[airPurifier2][i - 1];
        }

        map[airPurifier2][1] = 0;  // 공기청정기로 들어간 미세먼지 제거
    }

    // 남아있는 미세먼지 양 계산
    static int getRemainingDust() {
        int totalDustAmount = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    totalDustAmount += map[i][j];
                }
            }
        }

        return totalDustAmount;
    }
}
