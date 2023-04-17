import requests
import os
import time




url_list = ['http://25.24.74.142:8080/api/get',
             'http://25.24.74.142:8080/api/get', 
             'http://25.24.74.142:8080/api/get']
# в този списък трябва да има сървъри един от които е първия main сървъра който като се развали има резервни коитпя
index1 = 0
index2 = 0

while (index2 < 2):
    try:
        response = requests.get(url_list[index1])
        if(response.ok is True):
            index2 = 0
            os.system('cls')
            text1 = response.text
            text1 = text1.replace("{", "")
            text1 = text1.replace("}", "")
            text1 = text1.replace("[", "")
            text1 = text1.replace("]", "\n")
            text1 = text1.replace(",", ", ")
            text1 = text1.replace(":", " : ")
            print(text1)
            time.sleep(5)
    except requests.RequestException as exception:
         index1+=1
         if(index1 is len(url_list)):
            index2 += 1
            index1 = 0
    

os.system('cls')        
print("All server failed")
