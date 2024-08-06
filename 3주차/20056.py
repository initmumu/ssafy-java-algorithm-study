N, M, K = map(int, input().split())

fireballs = []
grid = [[[] for _ in range(N)] for _ in range(N)]

dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, 1, 1, 1, 0, -1, -1, -1]

for _ in range(M):
    r, c, m, s, d = map(int, input().split())
    fireballs.append([r-1, c-1, m, s, d])

for _ in range(K):
    while fireballs:
        r, c, m, s, d = fireballs.pop()
        nr = (N + r + s * dx[d]) % N
        nc = (N + c + s * dy[d]) % N

        grid[nr][nc].append([m, s, d])

    for r in range(N):
        for c in range(N):
            count = len(grid[r][c])

            if count == 1:
                fireballs.append([r, c] + grid[r][c].pop())

            elif count > 1:
                m_sum, s_sum, d_set = 0, 0, set()
                while grid[r][c]:
                    m, s, d = grid[r][c].pop()
                    m_sum += m
                    s_sum += s
                    d %= 2
                    d_set.add(d)

                m = m_sum // 5
                s = s_sum // count

                if len(d_set) == 1:
                    nd = [0, 2, 4, 6]
                else:
                    nd = [1, 3, 5, 7]

                if m:
                    for d in nd:
                        fireballs.append([r, c, m, s, d])
                else:
                    grid[r][c] = []

print(sum([fireball[2] for fireball in fireballs]))
