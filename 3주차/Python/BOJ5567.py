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

def bfs(g, start):
    # 결과를 저장할 리스트를 초기화합니다.
    result = []

    # BFS를 위한 큐를 초기화합니다. 큐에는 (노드, 현재 깊이) 쌍을 저장합니다.
    queue = deque([(start, 0)])
    
    # 방문한 노드를 기록합니다.
    visited = set([start])

    while queue:
        node, depth = queue.popleft()

        # 현재 깊이가 2가 넘으면 탐색을 중지합니다.
        if depth > 2:
            continue

        # 현재 노드를 깊이에 따라 결과 리스트에 추가합니다.
        if depth > 0:  # 루트 노드를 제외하므로 깊이가 0인 노드는 제외합니다.
            result.append(node)

        # 자식 노드들을 큐에 추가합니다.
        for neighbor in g[node]:
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append((neighbor, depth + 1))

    return result

n = int(input())
m = int(input())
g = dict()

readGraph(g, n, m)
print(len(bfs(g, 1)))

'''
6
5
1 2
2 3
1 4
4 5
3 6
'''