import sys
input = sys.stdin.readline

def listHashing(arr):
    return " ".join(map(str, arr))

def calcStat(statTable, selected):
    r = 0
    for i in range(len(selected)):
        for j in range(i+1, len(selected)):
            r += statTable[selected[i]][selected[j]] + statTable[selected[j]][selected[i]]
    return r


def combination(selected, idx, r, N, memoDict, stats):

    if len(selected) == r:
        hashed = listHashing(sorted(selected))
        if hashed not in memoDict:
            memoDict[hashed] = calcStat(stats, selected)
        return

    if idx >= N:
        return
    
    combination(selected + [idx], idx + 1, r, N, memoDict, stats)
    combination(selected, idx + 1, r, N, memoDict, stats)

def solution():
    N = int(input())
    
    stats = [tuple(map(int, input().split())) for _ in range(N)]

    memoDict = {}
    combination([], 0, N//2, N, memoDict, stats)

    minV = 2_000_000_000
    d = list(memoDict.items())
    l = len(d)
    for i in range(l//2):
        t = abs(d[i][1] - d[l-i-1][1]) 
        minV = t if t < minV else minV

    print(minV)

solution()