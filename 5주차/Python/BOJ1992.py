import sys
input = sys.stdin.readline

def dc(image, i, j, s):
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
        return f"({r})"
    


def solution(br):
    N = int(br())
    image = [list(input()) for _ in range(N)]

    a = dc(image, 0, 0, N)
    print(a)

    pass

if __name__ == "__main__":
    solution(input)