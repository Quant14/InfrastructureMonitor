import init_machine
import get_machine_stats

import requests
from time import sleep
import keyboard

ip = {1: "25.24.74.142:8080", 2: ""}


def first_connection(ip_key):
    try:
        url = "http://" + ip.get(ip_key) + "/api/switch-master"

        headers = {}
        payload = {}

        response = requests.request("POST", url, headers=headers, data=payload, timeout=10)
        return response
    except requests.RequestException as exception:
        print("Connection error.", exception)


def make_first_request(ip_key, name):
    try:
        url = "http://" + ip.get(ip_key) + "/api/connect"

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
        url = "http://" + ip.get(ip_key) + "/api/update"

        payload = get_machine_stats.get_machine_stats(name)
        headers = {}

        print(payload)

        response = requests.request("PUT", url, headers=headers, json=payload, timeout=10)
        return response
    except requests.RequestException as exception:
        print("Error!", exception)
    except KeyboardInterrupt:
        print("Program has been canceled.")
        disconnect_machine()
        exit(10)


def disconnect_machine():
    disconnect_url = "http://" + ip.get(ip_first_key) + "/api/disconnect"
    disconnect = requests.request("DELETE", disconnect_url, timeout=10)
    exit(5)


get_name = init_machine.get_name()

ip_first_key = 1

first_request = None
try:
    first_request = first_connection(ip_first_key)
except requests.ConnectionError as connection_exception:
    print("Initial connection error", connection_exception)
    ip_first_key += 1
    first_request = first_connection(ip_first_key)

while True:
    get_response = make_request(ip_first_key, get_name)
    if not get_response or get_response.ok is False:
        for i in range(3):
            print("Trying again.")
            get_response = make_request(ip_first_key, get_name)
            if get_response is True:
                break
            else:
                sleep(1.5)
        print("Switching server")
        ip_first_key += 1
    else:
        sleep(5)
        continue
