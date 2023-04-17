import json
import os as os


def init_machine():
    initialise = {
        "name": os.uname()[1]
    }
    send_name = json.dumps(initialise)
    return send_name


def get_name():
    while True:
        answer = int(input("1. Insert name for your machine\n2. Use your machine's default name\n"))
        if answer == 1:
            name = input("Enter the name for your machine:\n")
            return name
        elif answer == 2:
            return None
        else:
            print("Wrong input! Please select an option:\n")
            continue
