with open("input.txt") as f:
    ans = 0
    x = 1
    cycle = 1
    cond = []
    check = [20,60,100,140,180,220]
    ans2 = []
    output_row = ""
    pixel = 0
    for i in f:
        i = i .strip("\n")
        if i == "noop":
            if pixel == x or pixel == x-1 or pixel == x+1:
                output_row += "#"
            else:
                output_row += " "
            pixel += 1
            if pixel == 40:
                ans2.append(output_row)
                output_row = ""
                pixel = 0
            cycle += 1
            if cycle in check:
                ans += x*cycle
            
        else:
            i = i.split(" ")
            
            if pixel == x or pixel == x-1 or pixel == x+1:
                output_row += "#"
            else:
                output_row += " "
            pixel += 1
            if pixel == 40:
                ans2.append(output_row)
                output_row = ""
                pixel = 0
            cycle += 1
            if cycle in check:
                ans += x*cycle

            if pixel == x or pixel == x-1 or pixel == x+1:
                output_row += "#"
            else:
                output_row += " "
            pixel += 1
            if pixel == 40:
                ans2.append(output_row)
                output_row = ""
                pixel = 0
            cycle += 1
            x += int(i[1])
            if cycle in check:
                ans += x*cycle
    print(ans)

    for i in ans2:
        print(i)
