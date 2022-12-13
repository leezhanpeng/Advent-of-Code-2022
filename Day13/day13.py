import ast

def compare(list1, list2):
    if type(list1) != list:
        list1 = [list1]

    if type(list2) != list:
        list2 = [list2]

    index = 0
    while index != len(list1) and index != len(list2):
        if type(list1[index]) == list or type(list2[index]) == list:
            output = compare(list1[index], list2[index])
            if output != None:
                return output
        else:
            if list1[index] > list2[index]:
                return False
            elif list1[index] < list2[index]:
                return True
        index += 1
    
    if index == len(list1) and index == len(list2):
        return None
    if (index == len(list1)):
        return True
    return False

input = []
with open("input.txt") as f:
    for i in f:
        if i.strip("\n") != "":
            input.append(ast.literal_eval(i.strip("\n")))
        
result = 0
for i in range(0, len(input), 2):
    if compare(input[i], input[i + 1]):
        result += (i // 2) + 1

input.append([[2]])
input.append([[6]])

ans = []
for packet in input:
    inserted = False
    for i in range(len(ans)):
        if compare(packet, ans[i]):
            ans.insert(i, packet)
            inserted = True
            break
    if not inserted:
        ans.append(packet)

print(result)

print((ans.index([[2]]) + 1) * (ans.index([[6]]) + 1))
