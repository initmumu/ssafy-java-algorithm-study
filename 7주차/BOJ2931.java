import java.io.*;
import java.util.*;

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
            if (up == 1 && down == 1 && left == 1 && right == 1) {
                break;
            }
        }

        if (up == 1 && down == 1 && left == 0 && right == 0) {
            System.out.println(x + " " + y + " " + "|");
        }
        else if (up == 0 && down == 0 && left == 1 && right == 1) {
            System.out.println(x + " " + y + " " + "-");
        }
        else if (up == 1 && down == 1 && left == 1 && right == 1) {
            System.out.println(x + " " + y + " " + "+");
        }
        else if (up == 0 && down == 1 && left == 0 && right == 1) {
            System.out.println(x + " " + y + " " + "1");
        }
        else if (up == 1 && down == 0 && left == 0 && right == 1) {
            System.out.println(x + " " + y + " " + "2");
        }
        else if (up == 1 && down == 0 && left == 1 && right == 0) {
            System.out.println(x + " " + y + " " + "3");
        }
        else if (up == 0 && down == 1 && left == 1 && right ==0) {
            System.out.println(x + " " + y + " " + "4");
        }
    }
}
