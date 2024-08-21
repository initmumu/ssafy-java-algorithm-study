import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void DFS(int x, int y, int length) {

        // 구역에 전부 같은 숫자가 들어있는지 판단
        int n = map[y][x];
        boolean isSame = true;
        for(int ny = y; ny < y + length; ny++) {
            for(int nx = x; nx < x + length; nx++) {
                if(map[ny][nx] != n) {
                    isSame = false;
                    break;
                }
            }
            if(!isSame) break;
        }

        // 전부 같은 숫자일 경우 return
        if(isSame) {
            sb.append(n);
            return;
        }

        // 아닐 경우 괄호 열고 4등분
        sb.append('(');
        int nx = x + length / 2;
        int ny = y + length / 2;
        DFS(x, y, length / 2);
        DFS(nx, y, length / 2);
        DFS(x, ny, length / 2);
        DFS(nx, ny, length / 2);
        sb.append(')');
        // 괄호 닫기
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        // 입력
        for(int y = 0; y < N; y++) {
            String s = br.readLine();
            for(int x = 0; x < N; x++) {
                map[y][x] = s.charAt(x) - '0';
            }
        }

        DFS(0, 0, N);

        System.out.println(sb);
    }

}
