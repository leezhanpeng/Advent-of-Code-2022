import math

with open("input.txt") as f:
    monkeyitems = {}
    monkeyoper = {}
    monkeytest = {}
    monkeytrue = {}
    monkeyfalse = {}
    ans = {}
    curr = 0
    monkeyCount = 0
    lcm = 1
    for i in f:
        if i[:6] == "Monkey":
            monkeyCount += 1
            i = i.strip("\n").split(" ")
            idnum = int(i[1][:-1])
            curr = idnum
            monkeyitems[idnum] = []
            ans[idnum] = 0
            monkeyoper[idnum] = []
            monkeytest[idnum] = []
            monkeytrue[idnum] = []
            monkeyfalse[idnum] = []

        else:
            i = i.strip("\n").split(": ")
            i[0] = i[0].strip(" ")
            if i[0] == "Starting items":
                items = list(map(int, i[1].split(", ")))
                monkeyitems[curr] = items
            
            elif i[0] == "Operation":
                oper = i[1].split(" ")
                monkeyoper[curr] = oper
            
            elif i[0] == "Test":
                test = i[1].split(" ")
                monkeytest[curr] = int(test[-1])
                lcm *= int(test[-1])
            
            elif i[0] == "If true":
                passing = i[1].split(" ")
                monkeytrue[curr] = int(passing[-1])
            elif i[0] == "If false":
                passing = i[1].split(" ")
                monkeyfalse[curr] = int(passing[-1])

    # Adjust value between 20 and 10000 for both parts
    for i in range(10000):
        for j in range(monkeyCount):
            operation = monkeyoper[j]
            for item in monkeyitems[j]:
                ans[j] += 1
                if operation[-1] == "old":
                    val = item
                else:
                    val = int(operation[-1])

                if operation[-2] == "+":
                    item += val
                else:
                    item *= val
                
                # for Part 1
                #item /= 3
                #item = math.floor(item)

                testing = monkeytest[j]
                
                item %= lcm
                if item % testing == 0:
                    monkeyitems[monkeytrue[j]].append(item)
                else:
                    monkeyitems[monkeyfalse[j]].append(item) 

            monkeyitems[j] = []

    anslist = []
    for monkey in ans:
        anslist.append(ans[monkey])
    anslist.sort()
    print(anslist[-1] * anslist[-2])


            