import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] s1 = br.readLine().chars()
                .map(c -> c - 'A' + 1)
                .toArray();
        int[] s2 = br.readLine().chars()
                .map(c -> c - 'A' + 1)
                .toArray();

        int[] yeek = new int[s1.length];
        int j = 0;

        yeek[0] = s2[0] - s1[0] <= 0 ? s2[0] - s1[0] + 26 : s2[0] - s1[0];

        for(int i = 1; i < s1.length; i++){
            int n = s2[i] - s1[i];
            if(n <= 0) n += 26;
            yeek[i] = n;

            if(n == yeek[j]){
                j++;
            } else {
                j = 0;
            }
        }

        if(j % (s1.length - j) != 0) {
            j = 0;
        }
        for(int i = 0; i < s1.length - j; i++){
            System.out.print((char)(yeek[i] + 'A' - 1));
        }
    }
}