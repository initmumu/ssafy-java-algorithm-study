import sys
input = sys.stdin.readline

def solution(input, answer):

    N = int(input())
    stock = list(map(int, input().split()))

    maxPrice = 0
    income = 0
    
    for day in range(N-1, -1, -1):
        if stock[day] > maxPrice:
            maxPrice = stock[day]
        else:
            income += maxPrice - stock[day]

    answer[0] += f"{income}\n"
    pass

if __name__ == "__main__":
    T = int(input())
    answer = [""]
    for tc in range(T):
        # print(f"========테스트 케이스{tc}========")
        solution(input, answer)
    
    print(answer[0])