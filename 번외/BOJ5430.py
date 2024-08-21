import sys
input = sys.stdin.readline

from collections import deque

def solution(input):
    global string
    cmd = list(input())
    noe = int(input())

    arr = input().split(',')
    arr[0] = arr[0][1:]
    arr[-1] = arr[-1][:-2]
    if arr[0] == "":
        arr = deque()
    else:
        arr = deque(map(int, arr))

    pointer = 0
    for c in cmd:
        if c == "R":
            if not pointer: pointer = 1
            else: pointer = 0

        if c == "D":
            if not pointer: 
                if noe == 0:
                    string += "error\n"
                    return
                
                noe -= 1
                arr.popleft()
            
            else:
                if noe == 0:
                    string += "error\n"
                    return
                noe -= 1
                arr.pop()

    if not noe:
        string += "[]\n"
        return

    string += "["
    if not pointer:
        for i in arr:
            string += str(i) + ","

    else:
        for i in range(len(arr)):
            string += str(arr.pop()) + ","

    string = string[:-1]
    string += "]\n"
    pass

if __name__ == "__main__":
    T = int(input())
    string = ""
    for i in range(T):
        solution(input)

    print(string)