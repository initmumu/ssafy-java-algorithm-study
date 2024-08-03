'''
유니온-파인드(서로소 집합)
'''

def union(setList, n1, n2):
    setList[n2] = find(setList, n1)

def find(setList, n):
    index = n
    while index!=setList[index]:
        index = setList[index]
    return index

N = 300

unf = [i for i in range(N+1)]
union(unf, 1, 203)
print(find(unf, 203))