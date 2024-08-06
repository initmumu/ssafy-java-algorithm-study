N, M = map(int, input().split())
board = []

for _ in range(N):
    board.append(list(map(int, input().split())))

tetrominoes = [
    # ㅡ모양
    [(0, 0), (0, 1), (0, 2), (0, 3)],
    [(0, 0), (1, 0), (2, 0), (3, 0)],
    
    # ㅁ모양
    [(0, 0), (0, 1), (1, 0), (1, 1)],
    
    # L모양
    [(0, 0), (1, 0), (2, 0), (2, 1)],
    [(0, 1), (1, 1), (2, 1), (2, 0)],
    [(0, 0), (0, 1), (0, 2), (1, 0)],
    [(0, 0), (0, 1), (0, 2), (1, 2)],
    [(0, 0), (0, 1), (1, 0), (2, 0)],
    [(0, 0), (0, 1), (1, 1), (2, 1)],
    [(0, 0), (1, 0), (1, 1), (1, 2)],
    [(0, 2), (1, 0), (1, 1), (1, 2)],
    
    # ㅗ모양
    [(0, 0), (0, 1), (0, 2), (1, 1)],
    [(0, 1), (1, 1), (2, 1), (1, 0)],
    [(1, 0), (1, 1), (1, 2), (0, 1)],
    [(0, 0), (1, 0), (2, 0), (1, 1)],
    
    # z모양
    [(0, 0), (0, 1), (1, 1), (1, 2)],
    [(1, 0), (0, 1), (1, 1), (0, 2)],
    [(0, 0), (1, 0), (1, 1), (2, 1)],
    [(1, 0), (2, 0), (0, 1), (1, 1)]
    
]

maximum_sum = 0
for i in range(N):
    for j in range(M):
        for tetromino in tetrominoes:
            current_sum = 0
            count = 0
            for dx, dy in tetromino:
                x, y = i + dx, j + dy
                if 0 <= x < N and 0 <= y < M:
                    current_sum += board[x][y]
                else: break
                count += 1
            if count == 4:
                maximum_sum = max(maximum_sum, current_sum)
                    
print(maximum_sum)
