import ast

def compare(packet_one, packet_two):
    if type(packet_one) != list:
        packet_one = [packet_one]

    if type(packet_two) != list:
        packet_two = [packet_two]

    index = 0
    while index != len(packet_one) and index != len(packet_two):
        if type(packet_one[index]) == list or type(packet_two[index]) == list:
            output = compare(packet_one[index], packet_two[index])
            if output != None:
                return output

        else:
            if packet_one[index] > packet_two[index]:
                return False
            elif packet_one[index] < packet_two[index]:
                return True

        index += 1
    
    if index == len(packet_one) and index == len(packet_two):
        return None

    if (index == len(packet_one)):
        return True

    return False

packets = []
with open("input.txt") as f:
    for packet in f:
        if packet.strip("\n") != "":
            packets.append(ast.literal_eval(packet.strip("\n")))
        
part_one_answer = 0
for i in range(0, len(packets), 2):
    if compare(packets[i], packets[i + 1]):
        part_one_answer += (i // 2) + 1

index_one = 1
index_two = 2
for packet in packets:
    if compare(packet, [[2]]):
        index_one += 1
    if compare(packet, [[6]]):
        index_two += 1

# Part 1 Answer
print(part_one_answer)

# Part 2 Answer
print(index_one * index_two)