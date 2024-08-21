import java.util.*;
import java.io.*;

public class Main {
    public static void solution(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());

        StringTokenizer st;

        int[][] meetings = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings[i] = new int[]{start, end};
        }
        Arrays.sort(meetings, Comparator.comparingInt((int[] a) -> a[1]).thenComparingInt(a -> a[0]));

        int cnt = 0; int curEnd = -1;
        for (int i = 0; i < meetings.length; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];

            if (start < curEnd) continue;
            curEnd = end;
            cnt++;

        }

        System.out.println(cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(br);
    }
}