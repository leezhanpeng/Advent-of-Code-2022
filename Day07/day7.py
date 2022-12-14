def check(dir):
    if dir in memo:
        return memo[dir]
    if dir in storage:
        result = storage[dir]
    else:
        result = 0
    if dir in track:
        for direc in track[dir]:
            if direc in memo:
                result += memo[direc]
            else:
                result += check(direc)
    if dir not in memo:
        memo[dir] = result
    return result

memo = {}
all_dir = []
track = {}
storage = {}
curr = []
with open("input.txt") as f:
    for i in f:
        i = i.strip("\n").split(" ")

        if ' '.join(curr) not in all_dir:
            all_dir.append(' '.join(curr))

        if i[0] == "$":
            if i[1] == "cd":
                if i[2] == "..":
                    curr.pop()
                else:
                    curr.append(i[2])
        elif i[0] == "dir":
            temp = curr.copy()
            temp.append(i[1])
            if ' '.join(temp) not in all_dir:
                all_dir.append(' '.join(temp))
            if ' '.join(curr) not in track:
                track[' '.join(curr)] = [' '.join(temp)]
            else:
                if temp not in track[' '.join(curr)]:
                    track[' '.join(curr)].append(' '.join(temp))
        elif i[0] == "ls":
            continue
        else:
            size = int(i[0])
            if ' '.join(curr) not in storage:
                storage[' '.join(curr)] = size
            else:
                storage[' '.join(curr)] += size
part_one_answer = 0
for dir in all_dir:
    sizing = check(dir)
    if sizing <= 100000:
        part_one_answer += sizing

# Part 1 Answer
print(part_one_answer)

space_to_delete = memo["/"] - 40000000
all_used_spaces = list(memo.values())
all_used_spaces.sort()
for used_space in all_used_spaces:
    if space_to_delete < used_space:

        # Part 2 Answer
        print(used_space)
        break

