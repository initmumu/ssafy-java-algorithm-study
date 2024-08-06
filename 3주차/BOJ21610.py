N, M = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(N)]

dx = [0, -1, -1, -1, 0, 1, 1, 1]
dy = [-1, -1, 0, 1, 1, 1, 0, -1]
clouds = [[N-1, 0], [N-1, 1], [N-2, 0], [N-2, 1]]

for _ in range(M):
    d, s = map(int, input().split())
    rain = []
    visited = [[False] * N for _ in range(N)]
    
    while clouds:
        x, y = clouds.pop()
        nx = (N + x + dx[d-1] * s) % N
        ny = (N + y + dy[d-1] * s) % N
        rain.append([nx, ny])

    for x, y in rain:
        A[x][y] += 1
        visited[x][y] = True

    cx = [-1, -1, 1, 1]
    cy = [-1, 1, -1, 1]
    for x, y in rain:
        count = 0
        for k in range(4):
            nx = x + cx[k]
            ny = y + cy[k]
            if 0 <= nx < N and 0 <= ny < N and A[nx][ny] >= 1:
                count += 1
        A[x][y] += count

    for i in range(N):
        for j in range(N):
            if A[i][j] >= 2 and visited[i][j] == False:
                clouds.append([i, j])
                A[i][j] -= 2

result = 0
for i in range(N):
    result += sum(A[i])
print(result)
