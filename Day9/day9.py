def move(rope):
    for knot in range(len(rope) - 1):
        head = rope[knot]
        tail = rope[knot + 1]
        if abs(head[0] - tail[0]) == 2 and abs(head[1] - tail[1]) == 2:
            if head[0] > tail[0]:
                tail[0] += 1

                if head[1] > tail[1]:
                    tail[1] += 1
                else:
                    tail[1] -= 1

            else:
                tail[0] -= 1

                if head[1] > tail[1]:
                    tail[1] += 1
                else:
                    tail[1] -= 1

        elif abs(head[0] - tail[0]) == 2:
            tail[1] = head[1]

            if head[0] > tail[0]:
                tail[0] += 1
            else:
                tail[0] -= 1

        elif abs(head[1] - tail[1]) == 2:
            tail[0] = head[0]

            if head[1] > tail[1]:
                tail[1] += 1
            else:
                tail[1] -= 1

    return rope

rope = []
for i in range(10):
    rope.append([0, 0])

head = rope[0]

location_tracker_knot1 = []
location_tracker_knot9 = []

with open("input.txt") as f:
    for movement in f:
        direction, dist = movement.strip("\n").split(" ")
        dist = int(dist)

        for i in range(dist):
            if direction == "U":
                head[1] += 1

            elif direction == "D":
                head[1] -= 1
            
            elif direction == "L":
                head[0] -= 1
            
            else:
                head[0] += 1
            
            rope = move(rope)

            if rope[1] not in location_tracker_knot1:
                location_tracker_knot1.append(rope[1].copy())

            if rope[9] not in location_tracker_knot9:
                location_tracker_knot9.append(rope[9].copy())

# Part 1 Answer
print(len(location_tracker_knot1))

# Part 2 Answer
print(len(location_tracker_knot9))