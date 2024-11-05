import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ2141 {
    static class Village implements Comparable<Village> {
        int pos;
        int pop;

        Village(int pos, int pop) {
            this.pos = pos;
            this.pop = pop;
        }

        @Override
        public int compareTo(Village o) {
            return Integer.compare(this.pos, o.pos);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Village[] villages = new Village[N];
        long total = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int pop = Integer.parseInt(st.nextToken());
            villages[i] = new Village(pos, pop);
            total += pop;
        }

        Arrays.sort(villages);

        long result = 0;
        for (Village village : villages) {
            result += village.pop;
            if (result >= (total + 1) / 2) {
                System.out.println(village.pos);
                break;
            }
        }
    }
}
