# Function to generate priority of item that appears in both compartment of a rucksack
def rucksack_priority(items):
    item_count = len(items)
    item_tracker = {}

    for item in items[:item_count // 2]:
        if item not in item_tracker:
            item_tracker[item] = True
    
    for item in items[item_count // 2:]:
        if item in item_tracker:

            if item.isupper():
                return ord(item) - 65 + 27

            return ord(item) - 96

# Function to find the priority of badge amongst three rucksacks
def badge_priority(rucksack1, rucksack2, rucksack3):
    badge_tracker = {}

    for item in rucksack1:
        if item not in badge_tracker:
            badge_tracker[item] = True
    
    badge_tracker_2 = {}
    for item in rucksack2:
        if item in badge_tracker:
            badge_tracker_2[item] = True
    
    for item in rucksack3:
        if item in badge_tracker_2:

            if item.isupper():
                return ord(item) - 65 + 27

            return ord(item) - 96

with open("input.txt") as f:
    part_one_priority_sum = 0
    part_two_priority_sum = 0

    rucksacks = f.read().splitlines()

    for i in range(0, len(rucksacks), 3):
        part_one_priority_sum += rucksack_priority(rucksacks[i]) + rucksack_priority(rucksacks[i + 1]) + rucksack_priority(rucksacks[i + 2])

        part_two_priority_sum += badge_priority(rucksacks[i], rucksacks[i + 1], rucksacks[i + 2])
    
    # Part 1 Answer
    print(part_one_priority_sum)

    # Part 2 Answer
    print(part_two_priority_sum)