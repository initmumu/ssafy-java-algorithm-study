def solution(input):
    def checkTomato():
        익은_토마토 = []
        c = 0
        for h in range(H):
            for n in range(N):
                for m in range(M):
                    t = box[h][n][m]
                    if t == 1:
                        익은_토마토.append((h, n, m))
                    elif t == -1:
                        c+=1

        return 익은_토마토, c

    M, N, H = map(int, input().split())

    dx = [-1, 1, 0, 0, 0, 0]
    dy = [0, 0, -1, 1, 0, 0]
    dz = [0, 0, 0, 0, -1, 1]

    box = [[list(map(int, input().split())) for __ in range(N)] for _ in range(H)]
    
    day = 0
    익토, 빈공간 = checkTomato()

    while True:
        temp = []
        cnt = 0
        for 좌표 in 익토:
            for d in range(6):
                z = 좌표[0] + dz[d]
                y = 좌표[1] + dy[d]
                x = 좌표[2] + dx[d]

                if -1 < x < M and -1 < y < N and -1 < z < H and box[z][y][x] == 0:
                    cnt += 1
                    temp.append([z, y, x])
                    box[z][y][x] = 1

        익토 = temp
        day += 1
        
        if cnt == 0:
            break
        
        # print(f"========{day}=========")
        # for h in box:
        #     for p in h:
        #         print(p)
        
    check, gb = checkTomato()
    if len(check) != N * M * H - 빈공간:
        print(-1)

    else: print(day-1)
        

if __name__ == "__main__":
    import sys
    input = sys.stdin.readline
    solution(input)