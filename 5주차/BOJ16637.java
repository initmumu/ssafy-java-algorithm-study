import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int max;
    static int N;
    static int[] num;
    static char[] op;

    public static void DFS(int count, int n) {
        // DEPTH 에 도달한 경우 max 갱신
        if(count == N / 2 + 1) {
            max = Math.max(max, n);
            return;
        }

        // temp : n(계산결과) 변경 후 DFS 를 한 뒤에 다시 되돌리기 위한 변수
        // temp2 : 괄호로 묶은 계산식의 결과를 저장할 변수
        int temp = n, temp2 = num[count];
        if(count + 1 < N / 2 + 1) {
            
            // 현재 숫자와 다음 숫자를 괄호로 묶어 계산 후 n 에 저장
            temp2 = Cal(op[count], temp2, num[count + 1]);
            n = Cal(op[count - 1], n, temp2);
            DFS(count + 2, n);
        }
        n = temp;
        
        // 현재 숫자를 n 에 계산
        n = Cal(op[count - 1], n, num[count]);
        DFS(count + 1, n);
    }

    // 계산 함수
    public static int Cal(char op, int a, int b) {
        switch(op) {
            case '+':
                a += b;
                break;
            case '-':
                a -= b;
                break;
            case '*':
                a *= b;
                break;
        }

        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String s = br.readLine();

        // 숫자 저장할 배열
        num = new int[s.length() / 2 + 1];

        // 연산자 저장할 배열
        op = new char[s.length() / 2];

        // 숫자 저장
        int idx = 0;
        for(int i = 0; i < s.length(); i += 2) {
            num[idx++] = s.charAt(i) - '0';
        }

        // 연산자 저장
        idx = 0;
        for(int i = 1; i < s.length();  i+= 2) {
            op[idx++] = s.charAt(i);
        }

        // 첫번째 숫자는 괄호가 의미가 없기 때문에 count 를 1, 현재 값을 첫번째 값으로 시작
        max = Integer.MIN_VALUE;
        DFS(1, num[0]);

        System.out.println(max);
    }
}
