import requests

response = requests.get('http://localhost:8080/')
print(response.status_code)
print(response.text)