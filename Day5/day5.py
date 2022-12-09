with open("input.txt") as f:
    instructions = f.read().splitlines()    

    # The number of stacks can be calculated based on the number of chars in the first line of the puzzle input
    num_of_stacks = (len(instructions[0]) + 1) // 4

    # Create a skeleton of all the stacks for both parts of the problem
    # Could have used deep copy, but yea its fine lol
    part_one_stacks = []
    part_two_stacks = []
    for i in range(num_of_stacks):
        part_one_stacks.append([])
        part_two_stacks.append([])

    # Initialise the stacks with the crates
    index = 0
    while True:
        inst = instructions[index]

        # Stack building stops once the instruction reaches the indexes of the stacks
        if inst[1] == "1":
            break

        stack_num = 0
        for i in range(0, len(inst), 4):

            # A non empty space indicates that a crate exist
            if inst[i:i+3] != "   ":
                part_one_stacks[stack_num].insert(0, inst[i+1])
                part_two_stacks[stack_num].insert(0, inst[i+1])
            stack_num += 1
        index += 1

    # Value of "index" stays at the indexes of crates, +2 to hit the index of first movement instruction
    for inst in instructions[index + 2:]:
            inst = inst.split(" ")
            amount, from_stack, to_stack = int(inst[1]), int(inst[3]), int(inst[5])

            for i in range(amount):
                part_one_crate = part_one_stacks[from_stack - 1].pop()
                part_one_stacks[to_stack - 1].append(part_one_crate)

                part_two_crate = part_two_stacks[from_stack - 1].pop()
                if i == 0:
                    part_two_stacks[to_stack - 1].append(part_two_crate)
                else:
                    part_two_stacks[to_stack - 1].insert(-i, part_two_crate)
    
    part_one_answer = ""
    for stack in part_one_stacks:
        if len(stack) != 0:
            part_one_answer += stack[-1]

    part_two_answer = ""
    for stack in part_two_stacks:
        if len(stack) != 0:
            part_two_answer += stack[-1]

    # Part 1 Answer
    print(part_one_answer)

    # Part 2 Answer
    print(part_two_answer)
                