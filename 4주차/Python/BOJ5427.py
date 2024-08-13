from collections import deque

def test_case(input):
    T = int(input())
    
    for _ in range(T):
        solution(input)

def solution(input):
    w, h = map(int, input().split())
    building = [list(input().strip()) for _ in range(h)]

    canGo = deque()
    fires = deque()
    for i in range(h):
        for j in range(w):
            if building[i][j] == "@":
                building[i][j] = '.'
                canGo.append((i, j))
            elif building[i][j] == "*":
                building[i][j] = 0
                fires.append((i, j))

    def fireSpread(fires):
        dx = [0, 1, 0, -1]
        dy = [-1, 0, 1, 0]
        vst = [[False for __ in range(w)] for _ in range(h)]
        for f in fires:
            vst[f[0]][f[1]] = True
        while fires:
            for _ in range(len(fires)):
                f = fires.popleft()

                for idx in range(4):
                    nx = f[1] + dx[idx]
                    ny = f[0] + dy[idx]
                    if -1 < nx < w and -1 < ny < h and building[ny][nx] == '.' and not vst[ny][nx]:
                        building[ny][nx] = building[f[0]][f[1]] + 1
                        vst[ny][nx] = True
                        fires.append((ny, nx))

    def bfs(canGo):
        dx = [0, 1, 0, -1]
        dy = [-1, 0, 1, 0]

        depth = 0
        vst = [[False for __ in range(w)] for _ in range(h)]  # 방문 여부 체크

        while canGo:
            for _ in range(len(canGo)):
                p = canGo.popleft()
                if p[0] == 0 or p[0] == h-1 or p[1] == 0 or p[1] == w-1:
                    return print(depth + 1)

                for idx in range(4):
                    nx = p[1] + dx[idx]
                    ny = p[0] + dy[idx]
                    if -1 < nx < w and -1 < ny < h:
                        if (type(building[ny][nx]) == int and building[ny][nx] > depth + 1) or building[ny][nx] == ".":
                            if not vst[ny][nx]:  # 이미 방문하지 않은 경우에만
                                vst[ny][nx] = True  # 방문 체크
                                canGo.append((ny, nx))

            depth += 1
                    
        return print("IMPOSSIBLE")

    fireSpread(fires)
    bfs(canGo)

if __name__ == "__main__":
    import sys
    test_case(sys.stdin.readline)
