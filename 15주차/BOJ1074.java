import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1074 {

    static int visitingOrder = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int r = Integer.parseInt(input[1]);
        int c = Integer.parseInt(input[2]);

        int size = (int) Math.pow(2, N);

        find(size, r, c);
        System.out.println(visitingOrder);
    }

    static void find(int size, int r, int c) {

        if (size == 1) {
            return;
        }

        int nSize = size / 2;

        // 왼쪽 위칸
        if (r < nSize && c < nSize) {
            find(nSize, r, c);
        }
        // 오른쪽 위칸
        else if (r < nSize && c >= nSize) {
            visitingOrder += nSize * nSize;
            find(nSize, r, c - nSize);
        }
        // 왼쪽 아래칸
        else if (r >= nSize && c < nSize) {
            visitingOrder += 2 * nSize * nSize;
            find(nSize, r - nSize, c);
        }
        // 오른쪽 아래칸
        else {
            visitingOrder += 3 * nSize * nSize;
            find(nSize, r - nSize, c - nSize);
        }
    }
}
