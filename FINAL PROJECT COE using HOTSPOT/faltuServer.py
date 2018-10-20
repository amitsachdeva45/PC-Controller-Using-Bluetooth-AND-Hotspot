import socket,pickle,pyautogui
import os, sys,vlc
import win32com.client
import threading
import subprocess as sp
s=socket.socket()
host=""
port=50038
addr=(host,port)
s.bind(addr)
s.listen(5)
conn,addr=s.accept()
print addr
print conn.recv(1024)
conn.send("HI\n")

