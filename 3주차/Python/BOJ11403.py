import sys
from collections import deque
input = sys.stdin.readline

N = int(input())
adj = [None for _ in range(N)]
for _ in range(N):
    adj[_] = list(map(int, input().split()))

adjg = dict()
for i in range(N):
    adjg[i] = []

for i in range(N):
    for j in range(N):
        if adj[i][j]==1:
            adjg[i].append(j)

ans = [[0 for i in range(N)] for _ in range(N)]


def bfs(g, s, ans):
    need, visited = deque(), deque()
    cnt = 0
    need.append(s)
    while need:
        node = need.popleft()
        if node not in visited:
            if cnt != 0:
                visited.append(node)
            need.extend(g[node])
            cnt+=1
            

    for i in visited:
        ans[s][i] = 1

for i in range(N):
    bfs(adjg, i, ans)

for i in range(N):
    print(" ".join(map(str, ans[i])))