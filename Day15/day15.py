def man_dist(a, b):
    return abs(a[0] - b[0]) + abs(a[1] - b[1])
    

with open("input.txt") as f:
    tracker = {}
    closest = {}
    scanners = []
    beacons = []
    for i in f:
        i = i.strip("\n").split(" ")
        sx = int(i[2].strip(",").strip("x="))
        sy = int(i[3].strip(":").strip("y="))
        bx = int(i[-2].strip(",").strip("x="))
        by = int(i[-1].strip(":").strip("y="))

        tracker[(sx, sy)] = "s"
        if (sx, sy) not in scanners:
            scanners.append((sx, sy))
        tracker[(bx, by)] = "b"
        if (bx, by) not in beacons:
            beacons.append((bx, by))

        closest[(sx, sy)] = (bx, by)

    for VAL in range(4000000):
        gaps = []
        for scanner in scanners:
            vert = abs(scanner[1] - VAL)
            beacon = closest[scanner]
            dist = man_dist(scanner, beacon)
            smallestx = dist - vert
            if smallestx >= 0:
                cursmallx = scanner[0] - smallestx
                curbigx = scanner[0] + smallestx

                gaps.append((cursmallx, curbigx))

        gaps.sort(key= lambda x: (x[0], x[1]))

        checked_gaps = []

        for gap in gaps:
            gap_found = True
            if checked_gaps == []:
                checked_gaps.append(gap)
                continue

            for i in range(len(checked_gaps)):
                if gap[0] - checked_gaps[i][1] <= 1:
                    checked_gaps.append(gap)
                    gap_found = False
                    break
            
            if gap_found:
                print(f"This the gap: {gap[0] - 1, VAL}")
                break


        if VAL == 2000000:
            smallx = gaps[0][0]
            gaps.sort(key= lambda x: x[1])
            bigx = gaps[-1][1]
            result = bigx - smallx + 1
            for beacon in beacons:
                if beacon[1] == VAL:
                    result -= 1
            
            print(result)



        