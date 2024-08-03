''' 예제 입력 1
4 5 1
1 2
1 3
1 4
2 4
3 4
'''
'''예제 출력 1
1 2 4 3
1 2 3 4
'''

import sys
from collections import deque
input = sys.stdin.readline

def readGraph(g, n, m):
    for i in range(1, n+1):
        g[i] = list()

    for i in range(m):
        node1, node2 = map(int, input().split())
        g[node1].append(node2)
        g[node2].append(node1)

    for k in g:
        g[k].sort()

def dfs(g, start):
    needVisited, visited = [start], []

    while needVisited:
        node = needVisited.pop()
        if node not in visited:   
            needVisited.extend(g[node][::-1])
            visited.append(node)

    return " ".join(map(str,visited))

def bfs(g, start):
    needVisited, visited = deque(), deque()
    needVisited.append(start)

    while needVisited:
        node = needVisited.popleft()
        if node not in visited:
            needVisited.extend(g[node])
            visited.append(node)
    return " ".join(map(str,visited))



g = dict()
N, M, V = map(int, input().split())

readGraph(g, N, M)
print(dfs(g, V))
print(bfs(g, V))
