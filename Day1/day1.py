with open("input.txt") as f:
    calories_sum = []
    current_sum = 0
    for calories in f:
        calories = calories.strip("\n")
        if calories:
            current_sum += int(calories)
        else:
            calories_sum.append(current_sum)
            current_sum = 0
    calories_sum.sort(reverse=True)

    # Part 1 Answer
    print(calories_sum[0])

    # Part 2 Answer
    print(sum(calories_sum[:3]))