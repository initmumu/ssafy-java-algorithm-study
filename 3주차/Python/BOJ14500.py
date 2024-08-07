import sys
input = sys.stdin.readline

def solution(N, M, grid):
    def dfs(graph, sumSet, visited, sx, sy, curSum, depth, N, M):
        if f"{sx} {sy}" in visited:
            return
        visited.add(f"{sx} {sy}")
        
        if not (-1 < sx < N and -1 < sy < M):
            return
        
        curSum += graph[sx][sy]
    
        if depth == 3:
            sumSet.add(curSum)
            return
        
        dfs(graph, sumSet, visited, sx-1, sy, curSum, depth+1, N, M) # 상
        dfs(graph, sumSet, visited, sx+1, sy, curSum, depth+1, N, M) # 하
        dfs(graph, sumSet, visited, sx, sy-1, curSum, depth+1, N, M) # 좌
        dfs(graph, sumSet, visited, sx, sy+1, curSum, depth+1, N, M) # 우

    def bruteFind(graph, sumSet, N, M):
        for i in range(N):
            for j in range(M-2):
                block3hap = sum(graph[i][j:j+3])
                if -1 < i-1 < N:
                    sumSet.add(block3hap + graph[i-1][j+1])
                if -1 < i+1 < N:
                    sumSet.add(block3hap + graph[i+1][j+1])

    sumSet = set()
    for i in range(N):
        for j in range(M):
            visited = set()
            dfs(grid, sumSet, visited, i, j, 0, 0, N, M)

    bruteFind(grid, sumSet, N, M)
    grid = [[row[i] for row in grid] for i in range(len(grid[0]))]
    bruteFind(grid, sumSet, M, N)
    print(max(sumSet))

if __name__ == "__main__":
    N, M = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(N)]

    solution(N, M, grid)