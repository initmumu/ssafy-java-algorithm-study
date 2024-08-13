def test_case():
    import sys
    sys.setrecursionlimit(15000)
    input = sys.stdin.readline

    T = int(input())
    for tc in range(T):
        solution(input)

def solution(input):

    dx = [0, 0, -1, 1]
    dy = [-1, 1, 0, 0]

    def dfs(i, j, step):
        if field[i][j] == 0:
            return
        
        if vst[i][j]:
            return
    
        step[0] += 1

        for idx in range(4):
            nx = i + dx[idx]; ny = j + dy[idx]
            if -1 < nx < N and -1 < ny < M and field[nx][ny] == 1 and not vst[nx][ny]:
                vst[i][j] = True
                dfs(nx, ny, step)
                vst[i][j] = False

        vst[i][j] = True
                

    M, N, K = map(int, input().split())
    field = [[0 for __ in range(M)] for _ in range(N)]

    for _ in range(K):
        x, y = map(int, input().split())
        field[y][x] = 1

    ''' 위는 입력 '''

    vst = [[False for __ in range(M)]for _ in range(N)]
    step = [0]

    cnt = 0
    for i in range(N):
        for j in range(M):
            dfs(i, j, step)

            if step[0] != 0:
                step[0] = 0
                cnt += 1
    
    print(cnt)
    pass

if __name__ == "__main__":
    test_case()