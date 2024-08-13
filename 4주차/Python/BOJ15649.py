import sys
input = sys.stdin.readline

def solution():
    
    def permutation(cur, remain, depth):
        if depth == M:
            perms.append(" ".join(map(str, cur)))
            return
        
        for i in range(len(remain)):
            permutation(cur + [remain[i]], remain[:i] + remain[i+1:], depth+1)

        
    N, M = map(int, input().split())
    numbers = [i for i in range(1, N+1)]
    perms = []
    
    for i in range(len(numbers)):
        permutation([i+1], numbers[:i] + numbers[i+1:], 1)

    for i in perms:
        print(i)

    pass

solution()