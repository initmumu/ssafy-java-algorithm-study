'''마법사 상어와 파이어볼'''
from collections import defaultdict
import math
import sys
input = sys.stdin.readline

def solution(N, K,fireballs):
    def hashing(elem):
        return f"{elem[0]} {elem[1]}"

    dr = [-1, -1, 0, 1, 1, 1, 0, -1]
    dc = [0, 1, 1, 1, 0, -1, -1, -1]

    for cnt in range(K):
        fbList = defaultdict(list)

        for fb in fireballs:
            fb[0] = (fb[0] + (dr[fb[4]] * fb[3])) % N
            fb[1] = (fb[1] + (dc[fb[4]] * fb[3])) % N
            fbList[hashing(fb)].append(fb)

        temp = list()
        
        for p, fbs in fbList.items():
            # 파이어볼이 두 개 이상 있을 때
            if(len(fbs) > 1):
                r = fbs[0][0]
                c = fbs[0][1]

                mHap = 0
                sHap = 0
                isOdd = True
                isEven = True
                for fb in fbs:
                    mHap += fb[2]
                    sHap += fb[3]
                    if (isEven and fb[4] % 2 != 0): isEven = False
                    if (isOdd and fb[4] % 2 == 0): isOdd = False

                m = mHap // 5
                s = sHap // len(fbs)
                if m > 0:
                    if isOdd or isEven:
                        temp.extend([[r, c, m, s, 0],[r, c, m, s, 2],[r, c, m, s, 4],[r, c, m, s, 6]])
                    else:
                        temp.extend([[r, c, m, s, 1],[r, c, m, s, 3],[r, c, m, s, 5],[r, c, m, s, 7]])
            else:
                temp.append(fbs[0])
            
        fireballs = temp
    
    massHap = 0
    for fb in fireballs:
        massHap += fb[2]

    print(massHap)


if __name__ == "__main__":
    N, M, K = map(int, input().split())
    grid = [[0 for i in range(N)] for j in range(N)]

    # r: row_location, c: column_location, m: 질량, s: 속력, d: 방향
    fireballs = [list(map(int, input().split())) for _ in range(M)]
    solution(N,  K, fireballs)


