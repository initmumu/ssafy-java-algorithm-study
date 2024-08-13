# Author: 배승호
# Date:   24.08.07.Wed
# Title:  순열(Permutation)

# DFS를 이용한 구현
# 깊이 우선이기 때문에 모든 과정이 끝난 후 소팅을 할 필요가 없다.
# arr: Sorted List
# N:   뽑을 갯수
def permutation_dfs(arr, N):
    # 사용한 원소를 저장하는 리스트
    used = [False for _ in range(len(arr))]
    rl = list() # 순열들을 저장

    def peekElem(chosen, used, N):
        if len(chosen) == N:
            print(" ".join(map(str, chosen)))
            return
        
        for i in range(len(arr)):
            if not used[i]:
                chosen.append(arr[i])
                used[i] = True
                peekElem(chosen, used, N)
                used[i] = False
                chosen.pop()

    peekElem([], used, N)

N, M = map(int, input().split())
permutation_dfs([i for i in range(1, N+1)], M)