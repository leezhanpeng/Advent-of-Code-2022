
def find_path(board, start, end, is_part2):
    queue = [[start[0], start[1], 0]]
    completed = []
    while queue != []:
        check = queue.pop(0)
        if is_part2 and board[check[0]][check[1]] == 0:
            return(check[2])

        if not is_part2 and end == [check[0], check[1]]:
            return(check[2])
            
        if check[1] > 0:
            if (not is_part2 and board[check[0]][check[1] - 1] - board[check[0]][check[1]] <= 1) or (is_part2 and board[check[0]][check[1] - 1] - board[check[0]][check[1]] >= -1):
                if [check[0], check[1] - 1] not in completed:
                    queue.append([check[0], check[1] - 1, check[2] + 1])
                    completed.append([check[0], check[1] - 1])
        
        if check[1] < len(board[0]) - 1:
            if (not is_part2 and board[check[0]][check[1] + 1] - board[check[0]][check[1]] <= 1) or (is_part2 and board[check[0]][check[1] + 1] - board[check[0]][check[1]] >= -1):
                if [check[0], check[1] + 1] not in completed:
                    queue.append([check[0], check[1] + 1, check[2] + 1])
                    completed.append([check[0], check[1] + 1])

        if check[0] > 0:
            if (not is_part2 and board[check[0] - 1][check[1]] - board[check[0]][check[1]] <= 1) or (is_part2 and board[check[0] - 1][check[1]] - board[check[0]][check[1]] >= -1):
                if [check[0] - 1, check[1]] not in completed:
                    queue.append([check[0] - 1, check[1], check[2] + 1])
                    completed.append([check[0] - 1, check[1]])

        if check[0] < len(board) - 1:
            if (not is_part2 and board[check[0] + 1][check[1]] - board[check[0]][check[1]] <= 1) or (is_part2 and board[check[0] + 1][check[1]] - board[check[0]][check[1]] >= -1):
                if [check[0] + 1, check[1]] not in completed:
                    queue.append([check[0] + 1, check[1], check[2] + 1])
                    completed.append([check[0] + 1, check[1]])

    return float("inf")

board = []
with open("input.txt") as f:
    for i in f:
        if "S" in i:
            start = [len(board), i.index("S")]
        if "E" in i:
            end = [len(board), i.index("E")]
        row = list(map(lambda x: ord(x) - 97, list(i.strip("\n"))))
        board.append(row)

    board[start[0]][start[1]] = 0
    board[end[0]][end[1]] = 25
    print(find_path(board, start, end, False))
    print(find_path(board, end, "any", True))