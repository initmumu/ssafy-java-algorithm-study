import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1062 {

    static int N, K, maxReadableCount = 0;
    static String[] words;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        if (K < 5) {
            System.out.println(0);
            return;
        }
        if (K == 26) {
            System.out.println(N);
            return;
        }

        words = new String[N];
        visited = new boolean[26];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            words[i] = words[i].substring(4, words[i].length() - 4);
        }

        for (char c : new char[]{'a', 'n', 't', 'i', 'c'}) {
            visited[c - 'a'] = true;
        }

        teach(0, 0);
        System.out.println(maxReadableCount);
    }

    static void teach(int start, int count) {
        if (count == K - 5) {
            maxReadableCount = Math.max(maxReadableCount, countReadableWords());
            return;
        }

        for (int i = start; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                teach(i + 1, count + 1);
                visited[i] = false;
            }
        }
    }
    static int countReadableWords() {
        int count = 0;
        for (String word : words) {
            boolean readable = true;
            for (char c: word.toCharArray()) {
                if (!visited[c - 'a']) {
                    readable = false;
                    break;
                }
            }

            if (readable) {
                count++;
            }
        }

        return count;
    }
}
