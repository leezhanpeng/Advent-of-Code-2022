with open("input.txt") as f:
    datastream = f.readline().strip("\n")
    
    # Iterate subset of datastream and check for 0 duplicated characters
    for index in range(len(datastream) - 3):
        if len(set(datastream[index:index+4])) == 4:
            # Part 1 Answer
            print(index + 4)
            break

    # Same as above but with different subset size
    for index in range(len(datastream) - 13):
        if len(set(datastream[index:index+14])) == 14:
            # Part 2 Answer
            print(index + 14)
            break