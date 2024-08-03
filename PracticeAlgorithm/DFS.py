import 

graph = dict()
 
graph['A'] = ['B', 'C']
graph['B'] = ['A', 'D']
graph['C'] = ['A', 'G', 'H', 'I']
graph['D'] = ['B', 'E', 'F']
graph['E'] = ['D']
graph['F'] = ['D']
graph['G'] = ['C']
graph['H'] = ['C']
graph['I'] = ['C', 'J']
graph['J'] = ['I']

def dfs_list(graph, start):
    need_visited, visited = list(), list()

    need_visited.append(start)

    while need_visited:
        node = need_visited.pop()
        # 방문하지 않았다면?
        if node not in visited:
            # 방문 리스트에 노드 추가
            visited.append(node)
            # node에 인접한 노드들을 need_visited에 추가
            need_visited.extend(graph[node])

    return visited


def dfs_recursion(graph, start, visited=[]):
    visited.append(start)

    for node in graph[start]:
        if node not in visited:
            dfs_recursion(graph, node, visited)

    return visited

print(dfs_list(graph, "A"))
print(dfs_recursion(graph, "A"))