import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        ArrayDeque<Character> ad = new ArrayDeque<>();

        for(int i = 0; i < T.length(); i++){
            ad.addLast(T.charAt(i));
        }

        boolean op = false;
        while(ad.size() != S.length()){
            if(!op) {
                char c = ad.pollLast();
                if (c == 'B') op = true;
            } else{
                char c = ad.pollFirst();
                if(c == 'B') op = false;
            }
        }

        boolean isSame = true;
        int adSize = ad.size();
        if(!op) {
            for (int i = 0; i < adSize; i++) {
                if (ad.pollFirst() != S.charAt(i)) {
                    isSame = false;
                    break;
                }
            }
        }
        else{
            for (int i = 0; i < adSize; i++) {
                if (ad.pollLast() != S.charAt(i)) {
                    isSame = false;
                    break;
                }
            }
        }
        int result = isSame ? 1 : 0;
        System.out.print(result);
    }
}
