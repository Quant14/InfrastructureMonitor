import json
import os as os
import psutil


def get_machine_stats(name):
    if name is None:
        set_name = os.uname()[1]
    else:
        set_name = name
    stats = {
        # name of the machine
        "name": set_name,
        # get cpu usage average for 5 sec and return in percentage
        "cpuUsage": psutil.cpu_percent(5),
        # get RAM usage and return percentage of used RAM
        "ramUsage": psutil.virtual_memory()[2],
        # get disk usage of / directory and return in used percentage
        "diskUsage": psutil.disk_usage('/')[3]
    }
    return stats
