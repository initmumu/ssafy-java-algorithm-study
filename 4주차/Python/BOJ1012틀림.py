import sys
input = sys.stdin.readline

from collections import deque

def solution():
    def u(n1, n2):
        a = f(n1); b = f(n2)
        if (a > b): ul[n1] = b
        else: ul[n2] = a

    def f(node):
        if ul[node] != node:
            return f(ul[node])
        return node
    
    def dfs(visited, sx, sy, root):
        if f"{sx} {sy}" in visited:
            return
        
        visited.add(f"{sx} {sy}")

        for i in range(4):
            cx = sx + dx[i]; cy = sy + dy[i]
            if -1 < cx < N and -1 < cy < M and field[cx][cy] == 1:
                u(cx * M + cy, root)
                dfs(visited, cx, cy, root)


    N, M, K = map(int, input().split())
    field = [[0 for _ in range(M)] for __ in range(N)]
    ul = [i for i in range(N*M)]

    dx = (-1, 1, 0, 0); dy = (0, 0, -1, 1)

    for i in range(K):
        x, y = map(int, input().split())
        field[x][y] = 1

    vi = set()
    for i in range(0, N*M):
        
        dfs(vi, i//M, i%M, i)


    for i in range(5):
        for j in range(5):
            print(ul[i*5+j], end=" ")
        print()

    answer = [0 for i in range(N*M)]
    for item in ul:
        answer[item] += 1

    cnt = 0
    for item in answer:
        if item > 1:
            cnt += 1
    print(cnt)
    pass

TEST_CASE = int(input())
for tc in range(TEST_CASE):
    solution()
    