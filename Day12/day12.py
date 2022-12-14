def shortest_path_length(is_part_one, heightmap, start_position, *end_position):
    queue = [[start_position[0], start_position[1], 0]]
    tracker = [[start_position[0], start_position[1]]]

    while len(queue) > 0:
        current_position = queue.pop(0)

        if (is_part_one and current_position[:-1] == end_position[0]) or (not is_part_one and heightmap[current_position[0]][current_position[1]] == 0):
            return current_position[2]

        next_positions = []
        if current_position[0] > 0 and [current_position[0] - 1, current_position[1]] not in tracker:
            next_positions.append([current_position[0] - 1, current_position[1]])

        if current_position[0] < len(heightmap) - 1 and [current_position[0] + 1, current_position[1]] not in tracker:
            next_positions.append([current_position[0] + 1, current_position[1]])

        if current_position[1] > 0 and [current_position[0], current_position[1] - 1] not in tracker:
            next_positions.append([current_position[0], current_position[1] - 1])

        if current_position[1] < len(heightmap[current_position[0]]) - 1 and [current_position[0], current_position[1] + 1] not in tracker:
            next_positions.append([current_position[0], current_position[1] + 1])

        for next_position in next_positions:
            from_position, to_position = heightmap[next_position[0]][next_position[1]], heightmap[current_position[0]][current_position[1]]   
            if is_part_one:
                from_position, to_position = to_position, from_position

            if to_position - from_position <= 1:
                queue.append([next_position[0], next_position[1], current_position[2] + 1])
                tracker.append([next_position[0], next_position[1]])

    return float("inf")

heightmap = []
with open("input.txt") as f:
    for row in f:
        if "S" in row:
            starting_point = [len(heightmap), row.index("S")]
        if "E" in row:
            ending_point = [len(heightmap), row.index("E")]
        row = list(map(lambda x: ord(x) - 97, list(row.strip("\n"))))
        heightmap.append(row)

    heightmap[starting_point[0]][starting_point[1]] = 0
    heightmap[ending_point[0]][ending_point[1]] = 25

    # Part 1 Answer
    print(shortest_path_length(True, heightmap, starting_point, ending_point))
    
    # Part 2 Answer
    print(shortest_path_length(False, heightmap, ending_point))