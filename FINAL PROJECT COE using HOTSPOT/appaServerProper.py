import socket,pickle,pyautogui
import os, sys
s=socket.socket()
host=""
port=50033
addr=(host,port)
s.bind(addr)
s.listen(5)
conn,addr=s.accept()
print addr



def mouse():
    while True:
        data=conn.recv(1024)
        data1=data.split("\n")
        data2=data1[0].split(" ")
        print data2
        if int(data2[2])!=2:
            break
        if int(data2[3])==1 and int(data2[2])==2:
            pyautogui.moveTo((float(data2[0])*3.7),(float(data2[1])*2.43),duration=0.5)
        elif int(data2[3])==2 and int(data2[2])==2:
            pyautogui.click()#LEFT CLICK
        elif int(data2[3])==3 and int(data2[2])==2:
            x1,y1=pyautogui.position()
            pyautogui.click(int(x1),int(y1),button='right')
            print "o"


while True:
    data2=conn.recv(1024)
    data3=data2.split("\n")
    
    r=int(data3[0])
    if r==2:
        print "MOUSE"
        mouse()
    

	
                
