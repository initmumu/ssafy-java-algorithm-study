import java.util.Scanner;

public class BOJ1475 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String N = sc.nextLine();

        int [] num = new int[9];
        for (int i = 0; i < N.length(); i++) {
            int current_num = N.charAt(i) - '0';
            if (current_num != 9) {
                num[current_num]++;
            } else {
                num[6]++;
            }
        }
        num[6] = (int) Math.round((double) num[6] / 2);

        int max = 0;
        for (int i = 0; i < 9; i++) {
            if (num[i] > max) max = num[i];
        }
        System.out.println(max);
        sc.close();
    }
}
