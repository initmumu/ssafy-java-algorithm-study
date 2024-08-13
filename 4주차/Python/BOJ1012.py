import sys
input = sys.stdin.readline
from collections import deque


def solution():
    def toCoordinateString(xy):
        return f"{xy[0]} {xy[1]}"

    def dfs2dimArr(startXY):
        needVisit = deque()
        if field[startXY[0]][startXY[1]]:
            needVisit.append(startXY)
            dx = [-1, 1, 0, 0]
            dy = [0, 0, -1, 1]

            while needVisit:
                node = needVisit.pop()
                
                x = node[0]; y = node[1];
                for i in range(4):
                    nx = x + dx[i]
                    ny = y + dy[i]

                    if -1 < nx < N and -1 < ny < M and field[nx][ny] == 1 and toCoordinateString([nx, ny]) not in visit:
                        field[nx][ny] = 0
                        needVisit.append([nx, ny])
                    else:
                        continue;

    # 입력
    N, M, K = map(int, input().split())
    field = [[0 for _ in range(M)] for __ in range(N)]
    for _ in range(K):
        x, y = map(int, input().split())
        field[x][y] = 1

    cnt = [0]
    visit = set()
    for i in range(N):
        for j in range(M):
            dfs2dimArr([i, j])

    print(cnt[0])


if __name__ == '__main__':
    for i in range(int(input())):
        solution()