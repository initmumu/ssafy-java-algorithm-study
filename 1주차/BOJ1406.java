import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1406 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] initText = br.readLine().toCharArray();
        int M = Integer.parseInt(br.readLine());
        List<Character> txt = new LinkedList<>();

        for (char c : initText) txt.add(c);

        ListIterator<Character> it = txt.listIterator();
        while (it.hasNext()) it.next();

        for (int i = 0; i < M; i++) {
            String[] cmd = br.readLine().split(" ");
            switch (cmd[0]) {
                case "L":
                    if (it.hasPrevious()) it.previous();
                    break;
                case "D":
                    if (it.hasNext()) it.next();
                    break;
                case "B":
                    if (it.hasPrevious()){
                        it.previous();
                        it.remove();
                    }
                    break;
                case "P":
                    it.add(cmd[1].charAt(0));
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character character : txt) sb.append(character);
        System.out.println(sb);
    }
}
