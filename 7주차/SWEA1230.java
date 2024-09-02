import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA1230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int T = 1; T <= 10; T++) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<String> origin = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                origin.add(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            String[] command = br.readLine().split(" ");

            int i = 0;
            while (i < command.length) {
                if (command[i].equals("I")) {
                    int x = Integer.parseInt(command[i + 1]);
                    int y = Integer.parseInt(command[i + 2]);
                    i += 3;

                    ArrayList<String> tmp = new ArrayList<>();
                    for (int cnt = 0; cnt < y; cnt++) {
                        tmp.add(command[i]);
                        i++;
                    }

                    origin.addAll(x, tmp);
                } else if (command[i].equals("D")) {
                    int x = Integer.parseInt(command[i + 1]);
                    int y = Integer.parseInt(command[i + 2]);
                    i += 3;

                    for (int cnt = 0; cnt < y; cnt++) {
                        origin.remove(x);
                    }
                } else if (command[i].equals("A")) {
                    int y = Integer.parseInt(command[i + 1]);
                    i += 2;

                    ArrayList<String> tmp = new ArrayList<>();
                    for (int cnt = 0; cnt < y; cnt++) {
                        tmp.add(command[i]);
                        i++;
                    }

                    origin.addAll(tmp);
                } else {
                    break;
                }
            }

            System.out.print("#" + T + " ");
            for (int j = 0; j < Math.min(10, origin.size()); j++) {
                System.out.print(origin.get(j) + " ");
            }
            System.out.println();
        }
    }
}
