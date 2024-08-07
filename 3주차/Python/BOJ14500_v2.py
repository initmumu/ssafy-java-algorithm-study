import sys
input = sys.stdin.readline

def solution(N, M, grid):
    # 테트리스 블록 모양들 정의
    tetrominoes = [
        [(0, 0), (0, 1), (0, 2), (0, 3)],  # ----
        [(0, 0), (1, 0), (2, 0), (3, 0)],  # |
        [(0, 0), (0, 1), (1, 0), (1, 1)],  # ㅁ
        
        [(0, 0), (0, 1), (0, 2), (1, 2)],   # ㄱ
        [(0, 0), (0, 1), (0, 2), (1, 0)],  # ㄱ 반대
        [(1, 0), (1, 1), (1, 2), (0, 0)],  # ㄴ
        [(1, 0), (1, 1), (1, 2), (0, 2)],  # ㄴ 반대

        [(0, 0), (1, 0), (2, 0), (2, 1)],  # L
        [(0, 1), (1, 1), (2, 1), (2, 0)],  # L 반대
        [(0, 0), (1, 0), (2, 0), (0, 1)],  # L
        [(0, 1), (1, 1), (2, 1), (0, 0)],  # L 반대

        [(0, 0), (1, 0), (1, 1), (2, 1)],  # Z
        [(0, 1), (1, 1), (1, 0), (2, 0)],  # Z
        [(0, 0), (0, 1), (1, 1), (1, 2)],
        [(0, 2), (0, 1), (1, 1), (1, 0)],
        
        [(0, 0), (1, 0), (1, 1), (2, 1)],  # ㅏ
        [(0, 0), (0, 1), (1, 1), (1, 2)],  # ㅗ
        [(0, 0), (0, 1), (-1, 1), (-1, 2)],  # ㅜ
        [(0, 0), (0, 1), (1, 1), (1, 2)],  # ㅓ
    ]
    
    def get_tetromino_sum(x, y, shape):
        hap = 0
        for (dx, dy) in shape:
            if -1 < x+dx < N and -1 < y+dy < M:
                hap += grid[x + dx][y + dy]
            else:
                return 0
        return hap

    max_sum = 0

    for i in range(N):
        for j in range(M):
            for shape in tetrominoes:
                max_sum = max(max_sum, get_tetromino_sum(i, j, shape))

    print(max_sum)

if __name__ == "__main__":
    N, M = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(N)]
    solution(N, M, grid)
