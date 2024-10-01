import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ26266 {
	
    public static int charToNumber(char c) {
        return c - 'A';
    }

    public static char numberToChar(int n) {
        return (char) (n + 'A');
    }

    public static String repeatKey(String key) {
        int n = key.length();
        int[] pi = new int[n];

        // 접두사 배열을 계산하는 KMP 알고리즘
        for (int i = 1, j = 0; i < n; i++) {
            while (j > 0 && key.charAt(i) != key.charAt(j)) {
                j = pi[j - 1];
            }
            if (key.charAt(i) == key.charAt(j)) {
                pi[i] = ++j;
            }
        }

        // 패턴의 길이를 구함
        int patternLength = n - pi[n - 1];
        if (n % patternLength == 0) {
            return key.substring(0, patternLength);
        } else {
            return key;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        String second = br.readLine();
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < first.length(); i++) {
            int a = charToNumber(first.charAt(i));
            int b = charToNumber(second.charAt(i));
            int c = (b - a + 25) % 26;
            sb.append(numberToChar(c));
        }
        
        String key = sb.toString();
        key = repeatKey(key);
        System.out.println(key);
    }
}
