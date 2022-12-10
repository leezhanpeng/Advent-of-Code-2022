mapping = []
with open("input.txt") as f:
    for i in f:
        k = list(i.strip("\n"))
        j = list(map(int, k))
        mapping.append(j)

visible_tree_count = 0
max_scenic_score = 0
for i in range(len(mapping)):
    for j in range(len(mapping[i])):
        visible = False
        up, down, left, right = 0, 0, 0, 0

        for k in range(i - 1, -1, -1):
            up += 1
            if mapping[i][j] <= mapping[k][j]:
                break
            if k == 0:
                visible = True

        for k in range(i + 1, len(mapping)):
            down += 1
            if mapping[i][j] <= mapping[k][j]:
                break
            if k == len(mapping) - 1:
                visible = True

        for k in range(j - 1, -1, -1):
            left += 1
            if mapping[i][j] <= mapping[i][k]:
                break
            if k == 0:
                visible = True

        for k in range(j + 1, len(mapping[i])):
            right += 1
            if mapping[i][j] <= mapping[i][k]:
                break
            if k == len(mapping[i]) - 1:
                visible = True

        scenic_score = up*down*left*right
        
        if visible or scenic_score == 0:
            visible_tree_count += 1

        if scenic_score > max_scenic_score:
            max_scenic_score = scenic_score

# Part 1 Answer
print (visible_tree_count)

# Part 2 Answer
print(max_scenic_score)