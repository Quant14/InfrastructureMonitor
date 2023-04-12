import json
import os as os


def init_machine():
    initialise = {
        "name": os.uname()[1]
    }
    send_name = json.dumps(initialise)
    return send_name


get_name = init_machine()
print(get_name)
