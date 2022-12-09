with open("input.txt") as f:
    
    # There are only 9 possible permutations, simply store in dict and pull out when needed
    scores = {}

    scores["A X"] = [3+1, 0+3]
    scores["A Y"] = [6+2, 3+1]
    scores["A Z"] = [0+3, 6+2]
    scores["B X"] = [0+1, 0+1]
    scores["B Y"] = [3+2, 3+2]
    scores["B Z"] = [6+3, 6+3]
    scores["C X"] = [6+1, 0+2]
    scores["C Y"] = [0+2, 3+3]
    scores["C Z"] = [3+3, 6+1]

    part_one_score = 0
    part_two_score = 0
    for guide in f:
        part_one_score += scores[guide.strip("\n")][0]
        part_two_score += scores[guide.strip("\n")][1]

    # Part 1 Answer
    print(part_one_score)

    # Part 2 Answer
    print(part_two_score)