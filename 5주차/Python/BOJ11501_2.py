import sys
input = sys.stdin.readline

def searchHighestPrice(stock, day):
    maxPrice = stock[day]; index = -1
    for idx in range(day + 1, len(stock)):
        if maxPrice < stock[idx]:
            maxPrice, index = stock[idx], idx
    return maxPrice, index

def solution(input, answer):
    N = int(input())
    stock = list(map(int, input().split()))

    cellingDay = -1; income = 0; cnt = 0; buy = 0;
        
    for day in range(N):
        if day > cellingDay:
            maxP, cellingDay = searchHighestPrice(stock, day)
            if cellingDay == -1:
                continue
            else:
                # print(f"[주식 구매] 구매가: {stock[day]}")
                buy += stock[day]
                cnt += 1

        elif day == cellingDay:
            # print(f"[주식 판매] 판매가: {stock[day]}, 수량: {cnt}")
            income += maxP * cnt - buy
            cnt = 0
            buy = 0
        
        else:
            # print(f"[주식 구매] 구매가: {stock[day]}")
            buy += stock[day]
            cnt += 1
        

    answer[0] += f"{income}\n"
    pass

if __name__ == "__main__":
    T = int(input())
    answer = [""]
    for tc in range(T):
        # print(f"========테스트 케이스{tc}========")
        solution(input, answer)
    
    print(answer[0])