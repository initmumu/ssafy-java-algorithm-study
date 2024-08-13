import sys
input = sys.stdin.readline

from itertools import combinations

def listHashing(arr):
    return " ".join(map(str, arr))

def calcStat(statTable, selected):
    r = 0
    for i in range(len(selected)):
        for j in range(i+1, len(selected)):
            r += statTable[selected[i]][selected[j]] + statTable[selected[j]][selected[i]]
    return r


def solution():
    N = int(input())

    stats = [tuple(map(int, input().split())) for _ in range(N)]

    memoDict = {}

    for i in combinations(range(N), N//2):
        a = listHashing(i)
        if a not in memoDict:
            memoDict[a] = calcStat(stats, i)

    minV = 2_000_000_000
    d = list(memoDict.items())
    l = len(d)
    for i in range(l//2):
        t = abs(d[i][1] - d[l-i-1][1]) 
        minV = t if t < minV else minV

    print(minV)

solution()