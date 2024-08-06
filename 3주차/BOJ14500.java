import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14500 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<int[][]> tetrominoes = new ArrayList<int[][]>();

        // ㅡ모양
        tetrominoes.add(new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}});
        tetrominoes.add(new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}});

        // ㅁ모양
        tetrominoes.add(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}});

        // L모양
        tetrominoes.add(new int[][]{{0, 0}, {1, 0}, {2, 0}, {2, 1}});
        tetrominoes.add(new int[][]{{0, 1}, {1, 1}, {2, 1}, {2, 0}});
        tetrominoes.add(new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 0}});
        tetrominoes.add(new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 2}});
        tetrominoes.add(new int[][]{{0, 0}, {0, 1}, {1, 0}, {2, 0}});
        tetrominoes.add(new int[][]{{0, 0}, {0, 1}, {1, 1}, {2, 1}});
        tetrominoes.add(new int[][]{{0, 0}, {1, 0}, {1, 1}, {1, 2}});
        tetrominoes.add(new int[][]{{0, 2}, {1, 0}, {1, 1}, {1, 2}});

        // ㅗ모양
        tetrominoes.add(new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 1}});
        tetrominoes.add(new int[][]{{0, 1}, {1, 1}, {2, 1}, {1, 0}});
        tetrominoes.add(new int[][]{{1, 0}, {1, 1}, {1, 2}, {0, 1}});
        tetrominoes.add(new int[][]{{0, 0}, {1, 0}, {2, 0}, {1, 1}});

        // z모양
        tetrominoes.add(new int[][]{{0, 0}, {0, 1}, {1, 1}, {1, 2}});
        tetrominoes.add(new int[][]{{1, 0}, {0, 1}, {1, 1}, {0, 2}});
        tetrominoes.add(new int[][]{{0, 0}, {1, 0}, {1, 1}, {2, 1}});
        tetrominoes.add(new int[][]{{1, 0}, {2, 0}, {0, 1}, {1, 1}});

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int[][] tetromino : tetrominoes) {
                    int current_sum = 0;
                    int count = 0;
                    for (int[] t : tetromino) {
                        int x = i + t[0], y = j + t[1];
                        if ((0 <= x) && (x < N) && (0 <= y) && (y < M)){
                            current_sum += board[x][y];
                        } else break;
                        count++;
                    } if (count == 4) {
                        max = Math.max(max, current_sum);
                    }

                }
            }
        }
        System.out.println(max);
    }
}
