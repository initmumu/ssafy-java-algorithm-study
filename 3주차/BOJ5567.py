n, m = int(input()), int(input())
f1, f2 = set(), set()

stack = []
for _ in range(m):
    a, b = map(int, input().split())
    if a == 1 or b == 1: f1.add(a+b-1)
    else: stack.append([a, b])

while stack:
    a, b = stack.pop()
    if a in f1: f2.add(b)
    if b in f1: f2.add(a)

print(len(f1.union(f2)))
