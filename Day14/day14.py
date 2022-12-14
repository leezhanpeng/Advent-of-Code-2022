board = {}
board[(500, 0)] = False
deepest = -float("inf")
with open("input.txt") as f:
    current = [0, 0]
    for i in f:
        i = i.strip("\n").split(" -> ")
        current = list(map(int, i[0].split(",")))
        if current[1] > deepest:
            deepest = current[1]
        for j in range(1, len(i)):
            next_point = list(map(int, i[j].split(",")))
            if next_point[1] > deepest:
                deepest = next_point[1]

            if current[0] == next_point[0]:
                if current[1] > next_point[1]:
                    for k in range(next_point[1], current[1] + 1):
                        board[(current[0], k)] = True

                elif next_point[1] >= current[1]:
                    for k in range(current[1], next_point[1] + 1):
                        board[(current[0], k)] = True
                
            else:
                if current[0] > next_point[0]:
                    for k in range(next_point[0], current[0] + 1):
                        board[(k, current[1])] = True

                elif next_point[0] >= current[0]:
                    for k in range(current[0], next_point[0] + 1):
                        board[(k, current[1])] = True  

            current = next_point

rested_sand_count = 0
while not board[(500, 0)]:
    sand = [500, 0]
    while True:
        if sand[1] == deepest + 1:
            board[tuple(sand)] = True
            rested_sand_count += 1
            break

        if (sand[0], sand[1] + 1) not in board:
            sand[1] += 1
            continue

        elif (sand[0] - 1, sand[1] + 1) not in board:
            sand = [sand[0] - 1, sand[1] + 1]
            continue

        elif (sand[0] + 1, sand[1] + 1) not in board:
            sand = [sand[0] + 1, sand[1] + 1]
            continue

        else:
            board[tuple(sand)] = True
            rested_sand_count += 1
            break

print(rested_sand_count) 