with open("input.txt") as f:
    part_one_count = 0
    part_two_count = 0

    for section_pair in f:
        section_one, section_two = section_pair.strip("\n").split(",")
        
        start_one, end_one = list(map(int, section_one.split("-")))
        start_two, end_two = list(map(int, section_two.split("-")))

        # To calculate number of pairs that encapsulate one another
        if (start_one >= start_two and end_one <= end_two) or (start_two >= start_one and end_two <= end_one):
            part_one_count += 1

        # To calculate number of pairs that overlap one another
        if (start_one <= end_two and start_two <= end_one) or (start_two <= end_one and start_one <= end_two):
            part_two_count += 1

    # Part 1 Answer
    print(part_one_count)

    # Part 2 Answer
    print(part_two_count)