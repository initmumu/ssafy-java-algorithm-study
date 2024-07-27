import java.util.Arrays;

public class Main {
    static int curNum, ascii;
    public static void main(String[] args) throws Exception {
        int N = readInt();

        int[] numArr = new int[N];
        int[] numArrReversed = new int[N];
        int[] stack = new int[N];
        int[] answer = new int[N];
        int stackSize = 1;

        for (int i = 0; i < N; i++) numArr[i] = readInt();

        int j = 0;
        for (int i = N - 1; i > -1; i--) {
            numArrReversed[j] = numArr[i];
            j++;
        }

        for (int i = 1; i < N; i++) {
            while (stackSize != 0 && numArrReversed[stack[stackSize - 1]] < numArrReversed[i]) {
                answer[stack[--stackSize]] = N - i;
            }
            stack[stackSize++] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = N-1; i > -1; i--) sb.append(answer[i]).append(" ");
        System.out.println(sb);
    }

    public static int readInt() throws Exception {
        curNum = 0;
        while((ascii=System.in.read()) > 47) { curNum = (curNum << 3) + (curNum << 1) + (ascii & 15); }
        return curNum;
    }
}
