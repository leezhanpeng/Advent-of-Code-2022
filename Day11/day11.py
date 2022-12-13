import math

class Monkey:
    def __init__(self):
        self.id = 0
        self.items = []
        self.operator = ""
        self.operant = 0
        self.divisor = 0
        self.true_pass = 0
        self.false_pass = 0
        self.items_inspected = 0

def monkey_business(rounds, monkeys, divisor_CM, is_part_one):
    for round in range(rounds):
        for monkey_id in range(len(monkeys)):
            monkey = monkeys[monkey_id]
            for item in monkey.items:
                monkey.items_inspected += 1

                if monkey.operant == "old":
                    worry_val = item
                else:
                    worry_val = int(monkey.operant)
                
                if monkey.operator == "+":
                    worry_val += item
                else:
                    worry_val *= item
                
                if is_part_one:
                    worry_val /= 3
                    worry_val = math.floor(worry_val)
                else:
                    worry_val %= divisor_CM

                if worry_val % monkey.divisor == 0:
                    monkeys[monkey.true_pass].items.append(worry_val)
                else:
                    monkeys[monkey.false_pass].items.append(worry_val)
                
            monkey.items = []

    business_count = []
    for monkey_id in monkeys:
        business_count.append(monkeys[monkey_id].items_inspected)
    business_count.sort()

    return business_count[-1] * business_count[-2]


divisor_CM = 1

part_one_monkeys = {}
part_two_monkeys = {}
with open("input.txt") as f:
    for desc in f:
        desc = desc.strip("\n").split(": ")
        if len(desc) == 1:
            type = desc[0]
        else:
            type, value = desc
            value = value.replace(",", "").split(" ")
        type = type.strip(" ").split(" ")
        if type[0] == "":
            continue
        if type[0] == "Monkey":
            part_one_monkey = Monkey()
            part_one_monkey.id = int(type[1][:-1])
            part_one_monkeys[part_one_monkey.id] = part_one_monkey

            part_two_monkey = Monkey()
            part_two_monkey.id = int(type[1][:-1])
            part_two_monkeys[part_two_monkey.id] = part_two_monkey
            
        elif type[0] == "Starting":
            for item in value:
                part_one_monkey.items.append(int(item))

                part_two_monkey.items.append(int(item))

        elif type[0] == "Operation":
            part_one_monkey.operator = value[-2]
            part_one_monkey.operant = value[-1]

            part_two_monkey.operator = value[-2]
            part_two_monkey.operant = value[-1]

        elif type[0] == "Test":
            divisor_CM *= int(value[-1])

            part_one_monkey.divisor = int(value[-1])

            part_two_monkey.divisor = int(value[-1])


        elif type[1] == "true":
            part_one_monkey.true_pass = int(value[-1])

            part_two_monkey.true_pass = int(value[-1])

        elif type[1] == "false":
            part_one_monkey.false_pass = int(value[-1])           

            part_two_monkey.false_pass = int(value[-1])            

# Part 1 Answer
print(monkey_business(20, part_one_monkeys, divisor_CM, True))
# Part 2 Answer
print(monkey_business(10000, part_two_monkeys, divisor_CM, False))