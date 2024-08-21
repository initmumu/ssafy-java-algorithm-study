import sys
input = sys.stdin.readline

cnt=0; cnt2 = 0;

def dc(image, i, j, s):
    global cnt, cnt2;
    if s == 1:
        if image[i][j] == "0":
            return "0"
        else:
            return "1"

    t = s // 2
    a = dc(image, i, j, t)
    b = dc(image, i, j+t, t)
    c = dc(image, i+t, j, t)
    d = dc(image, i+t, j+t, t)

    r = a+b+c+d

    if r == "0000":
        return "0"
    elif r == "1111":
        return "1"
    else: 
        cnt += r.count("1") # b
        cnt2 += r.count("0") # w
        return "";
    
def solution(br):
    N = int(br())
    image = [input().split() for _ in range(N)]

    a = dc(image, 0, 0, N)
    global cnt, cnt2
    cnt += a.count("1")
    cnt2 += a.count("0")
    print(cnt2)
    print(cnt)

    pass

if __name__ == "__main__":
    solution(input)