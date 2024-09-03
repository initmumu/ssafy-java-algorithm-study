import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int x = 0, y = 0;
        int up = 0, down = 0, left = 0, right = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '|') {
                    if (map[i - 1][j] == '.') {
                        x = i;
                        y = j + 1;
                        down++;
                    }
                    if (map[i + 1][j] == '.') {
                        x = i + 2;
                        y = j + 1;
                        up++;
                    }
                }
                if (map[i][j] == '-') {
                    if (map[i][j - 1] == '.') {
                        x = i + 1;
                        y = j;
                        right++;
                    }
                    if (map[i][j + 1] == '.') {
                        x = i + 1;
                        y = j + 2;
                        left++;
                    }
                }
                if (map[i][j] == '+') {
                    if (map[i - 1][j] == '.') {
                        x = i;
                        y = j + 1;
                        down++;
                    }
                    if (map[i + 1][j] == '.') {
                        x = i + 2;
                        y = j + 1;
                        up++;
                    }
                    if (map[i][j - 1] == '.') {
                        x = i + 1;
                        y = j;
                        right++;
                    }
                    if (map[i][j + 1] == '.') {
                        x = i + 1;
                        y = j + 2;
                        left++;
                    }
                }
                if (map[i][j] == '1') {
                    if (map[i + 1][j] == '.') {
                        x = i + 2;
                        y = j + 1;
                        up++;
                    }
                    if (map[i][j + 1] == '.') {
                        x = i + 1;
                        y = j + 2;
                        left++;
                    }
                }
                if (map[i][j] == '2') {
                    if (map[i - 1][j] == '.') {
                        x = i;
                        y = j + 1;
                        down++;
                    }
                    if (map[i][j + 1] == '.') {
                        x = i + 1;
                        y = j + 2;
                        left++;
                    }
                }
                if (map[i][j] == '3') {
                    if (map[i][j - 1] == '.') {
                        x = i + 1;
                        y = j;
                        right++;
                    }
                    if (map[i - 1][j] == '.') {
                        x = i;
                        y = j + 1;
                        down++;
                    }
                }
                if (map[i][j] == '4') {
                    if (map[i][j - 1] == '.') {
                        x = i + 1;
                        y = j;
                        right++;
                    }
                    if (map[i + 1][j] == '.') {
                        x = i + 2;
                        y = j + 1;
                        up++;
                    }
                }
            }
        }

        char result = ' ';
        if (up == 1 && down == 1 && left == 0 && right == 0) {
            result = '|';
        }
        else if (up == 0 && down == 0 && left == 1 && right == 1) {
            result = '-';
        }
        else if (up == 1 && down == 1 && left == 1 && right == 1) {
            result = '+';
        }
        else if (up == 0 && down == 1 && left == 0 && right == 1) {
            result = '1';
        }
        else if (up == 1 && down == 0 && left == 0 && right == 1) {
            result = '2';
        }
        else if (up == 1 && down == 0 && left == 1 && right == 0) {
            result = '3';
        }
        else if (up == 0 && down == 1 && left == 1 && right ==0) {
            result = '4';
        }

        System.out.println(x + " " + y + " " + result);
    }
}
