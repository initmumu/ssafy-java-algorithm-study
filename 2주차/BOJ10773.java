import java.util.Arrays;

public class Main {
    static int curNum, ascii;
    public static void main(String[] args) throws Exception {
        int N = readInt();

        int[] sumArr = new int[100001];
        int arrSize = 0;

        int n;
        for (int i = 0; i < N; i++) {
            if ((n = readInt()) != 0) {
                if (arrSize == 0) {sumArr[arrSize++] = n;}
                else {
                    int a = sumArr[arrSize-1] + n;
                    sumArr[arrSize++] = a;
                }
            } else {
                arrSize--;
            }
        }
        System.out.println((arrSize == 0)?0:sumArr[arrSize - 1]);
    }

    public static int readInt() throws Exception {
        curNum = 0;
        while((ascii=System.in.read()) > 47) { curNum = (curNum << 3) + (curNum << 1) + (ascii & 15); }
        return curNum;
    }
}
