def cycle_execution(pixel_index, reg_X, current_CRT_row, CRT_image):
    if pixel_index == reg_X or pixel_index == reg_X - 1 or pixel_index == reg_X + 1:
        current_CRT_row += "#"
    else:
        current_CRT_row += " "

    pixel_index += 1

    if pixel_index == 40:
        CRT_image.append(current_CRT_row)
        current_CRT_row = ""
        pixel_index = 0

    return pixel_index, current_CRT_row

def check_signal_strength(cycle_count, reg_X):
    if (cycle_count - 20) % 40 == 0:
        return cycle_count * reg_X

    return 0

with open("input.txt") as f:
    signal_sum = 0
    reg_X = 1
    cycle_count = 0
    CRT_image = []

    current_CRT_row = ""
    pixel_index = 0

    for instruction in f:
        instruction = instruction.strip("\n")
        pixel_index, current_CRT_row = cycle_execution(pixel_index, reg_X, current_CRT_row, CRT_image)
        cycle_count += 1
        signal_sum += check_signal_strength(cycle_count, reg_X)

        if instruction != "noop":
            cmd, value = instruction.split(" ")
            pixel_index, current_CRT_row = cycle_execution(pixel_index, reg_X, current_CRT_row, CRT_image)
            cycle_count += 1
            reg_X += int(value)
            signal_sum += check_signal_strength(cycle_count, reg_X)

    # Part 1 Answer
    print(signal_sum)

    # Part 2 Answer
    for CRT_row in CRT_image:
        print(CRT_row)