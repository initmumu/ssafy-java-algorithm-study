import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class BOJ_1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String string = br.readLine();
        int M = Integer.parseInt(br.readLine());

        LinkedList<Character> list = new LinkedList<>();

        for (int i = 0; i < string.length(); i++) {
            list.add(string.charAt(i));
        }

        ListIterator<Character> iterator = list.listIterator();
        while (iterator.hasNext()) iterator.next();

        for (int i = 0; i < M; i++) {
            String command = br.readLine();
            switch (command.charAt(0)) {
                case 'L':
                    if (iterator.hasPrevious()) iterator.previous();
                    break;
                case 'D':
                    if (iterator.hasNext()) iterator.next();
                    break;
                case 'B':
                    if (iterator.hasPrevious()) {
                        iterator.previous();
                        iterator.remove();
                    }
                    break;
                case 'P':
                    iterator.add(command.charAt(2));
                    break;
            }
        }

        StringBuffer sb = new StringBuffer();
        for (Character c : list) {
            sb.append(c);
        }

        System.out.println(sb.toString());
    }
}
