import sys
print = sys.stdout.write

def solution():
    def combinationMultisets(chosen, idx):
        if len(chosen) == R:
            result.add(" ".join(map(str, chosen)) + "\n")
            return
        
        if idx >= N:
            return
        
        combinationMultisets(chosen + [idx+1], idx) # 넣고 안넘어가기
        combinationMultisets(chosen + [idx+1], idx+1) # 넣고 다음 수로 넘어가기
        combinationMultisets(chosen, idx+1) # 안 넣고 다음 수로 넘어가기
        
        
    N, R = map(int, input().split())
    result = set()
    combinationMultisets([], 0)

    for r in sorted(list(result)):
        print(r)

solution()