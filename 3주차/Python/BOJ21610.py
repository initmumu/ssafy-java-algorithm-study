import sys
input = sys.stdin.readline
def s():
    def hashing(elem):
        a = elem[0]
        b = elem[1]
        return f"{a} {b}"
    
    N, M = map(int, input().split()) # N: 배열의 크기, M: 명령의 수
    buckets = [list(map(int, input().split())) for j in range(N)]
    cmd = [list(map(int, input().split())) for i in range(M)]

    # 1: 왼, 2:왼상, 3:위, 4:오상, 5:오, 6:오하, 7:아래, 8: 왼하
    directions = [(0, 0), (0, -1), (-1,-1), (-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1)]
    clouds = [[N-2, 1], [N-2, 0], [N-1, 0], [N-1, 1]]
    
    

    for i in range(M):
        d, c = cmd[i][0], cmd[i][1]
        # 이동 횟수가 N이면 제자리
        c %= N
        dx, dy = directions[d][0] * c, directions[d][1] * c
        for cloud in clouds:
            cloud[0] = (cloud[0] + dx) % N
            cloud[1] = (cloud[1] + dy) % N
            buckets[cloud[0]][cloud[1]] += 1
        c2 = set(map(hashing, clouds))
            
        for cloud in clouds:
            x, y = cloud[0], cloud[1]
            os = [[x-1, y-1], [x-1, y+1], [x+1, y-1], [x+1, y+1]]
            cnt = 0
            for o in os:
                ox, oy = o[0], o[1]
                if -1 < ox < N and -1 < oy < N:
                    if buckets[ox][oy] > 0:
                        cnt+=1
            buckets[x][y] += cnt

        temp = []
        for i in range(N):
            for j in range(N):
                if buckets[i][j] > 1 and f"{i} {j}" not in c2:
                    temp.append([i,j])            
                    buckets[i][j] -= 2

        clouds = temp

    sumNum = 0
    for i in buckets:
        sumNum += sum(i)

    print(sumNum)

s()
    
