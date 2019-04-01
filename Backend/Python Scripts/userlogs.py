import requests
import json

fp = open("logs.txt","r")
URL = "https://us-central1-quicktap-b9bc9.cloudfunctions.net/app/savelogs"
for line in fp:
    lineSplit = line.split(',')
    amount = lineSplit[0]
    senderId = lineSplit[1]
    receiverId = lineSplit[2]
    timestamp = lineSplit[3]
    data = {
        "amount": amount,
        "senderId": senderId,
        "receiverId": receiverId,
        "timestamp": timestamp
    }
    json_data = json.dumps(data)
    r = requests.post('https://us-central1-quicktap-b9bc9.cloudfunctions.net/app/savelogs', data = json_data)
    print(r)

