import init_machine
import get_machine_stats

import requests
from time import sleep

ip = {1: "25.24.74.142", 2: ""}


def make_first_request(ip_key, name):
    try:
        url = "http://" + ip.get(ip_key) + ":8080/api/connect?name=machine1"

        headers = {}

        if name is None:
            payload = init_machine.init_machine()
        else:
            payload = {"name": name}

        response = requests.request("POST", url, headers=headers, data=payload, timeout=10)
        return response
    except KeyboardInterrupt:
        print("Program has been canceled.")


def make_request(ip_key, name):
    try:
        url = "http://" + ip.get(ip_key) + ":8080/api/connect?name=machine1"

        payload = get_machine_stats.get_machine_stats(name)
        headers = {}

        response = requests.request("POST", url, headers=headers, data=payload, timeout=10)
        return response.ok
    except KeyboardInterrupt:
        print("Program has been canceled.")


get_name = init_machine.get_name()

ip_first_key = 1

first_response = None

while first_response is None:
    try:
        first_response = make_first_request(ip_first_key, get_name)
    except requests.ConnectionError as connection_exception:
        print("Initial connection error", connection_exception)
        sleep(1.5)
        continue
    except requests.Timeout as timeout_exception:
        print("Request timed out", timeout_exception)
        sleep(1.5)
        continue
    except requests.RequestException as general_exception:
        print("Error occurred with request", general_exception)
        sleep(1.5)
        continue

while True:
    get_response = make_request(ip_first_key)
    if get_response is None:
        for i in range(3):
            get_response = make_request(ip_first_key)
            sleep(1.5)
    if requests.RequestException:
        print("Switching server")
        ip_first_key += 1
    sleep(5)
