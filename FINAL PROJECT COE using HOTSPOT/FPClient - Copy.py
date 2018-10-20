import os
import socket
import sys,time
import Tkinter as tk
import tkMessageBox
from Tkinter import *
from functools import partial
import vlc
import pickle,win32gui,pyautogui
from socket import *


host='192.168.43.217'
port=5081
addr=(host, port)
s = socket(AF_INET,SOCK_DGRAM)
s.connect((host,port))
#                 MUSIC
value=0
i1=0
p1=0
root=0
frame=0
mainflag=2
print addr
def onFrameConfigure(canvas):
    '''Reset the scroll region to encompass the inner frame'''
    canvas.configure(scrollregion=canvas.bbox("all"))
def Tkinter1():
    global root
    global frame
    root = tk.Tk()
    root.title("MUSIC PLAYER")
    w = Label(root, text="MUSIC PLAYER", font=("Helvetica", 16) ,fg="RED")
    w.pack()
    root.geometry('700x600+100+100')

##SCROLLBAR
    canvas = tk.Canvas(root, borderwidth=0, background="#ffffff")
    frame = tk.Frame(canvas, background="#ffffff")
    vsb = tk.Scrollbar(root, orient="vertical", command=canvas.yview)
    canvas.configure(yscrollcommand=vsb.set)

    vsb.pack(side="right", fill="y")
    canvas.pack(side="right", fill="both", expand=True)
    canvas.create_window((4,4), window=frame, anchor="nw")

    frame.bind("<Configure>", lambda event, canvas=canvas: onFrameConfigure(canvas))

###SCROLLBAAR


def helloCallBack(path,mp3):
   #global addr
   global i1
   global value
   global p1
   f=[]
   f.append(0)
   
   r="file:///"+path+"/" + mp3
   f.append(r)
   f.append(2)
   print addr
   s.sendto(pickle.dumps(f),addr)
   
   """if i1==0:
       value=r
       i1=i1+1
       p = vlc.MediaPlayer(r)
       p.play()
       p1=p
       print "i=0"
   else:
       p1.stop()
       print value
       p = vlc.MediaPlayer(r)
       p.play()
       print r
       value=r
       p1=p
       print value
       print "HAHA"""
        
        
        
   
   
   #tkMessageBox.showinfo( path,r)
   
   
   
   
   
   


#Input: A path to a folder
#Output: List containing paths to all of the nested folders of path
ii=0
num1=2
def helloCallBack1(num):
    global ii
    global num1
    global mainflag
    global addr
    ff=[]
    ff.append(0)
    ff.append(0)
    ff.append(num)
    ff.append(5)#ESPECIALLY FOR MOUSE
    s.sendto(pickle.dumps(ff),addr)
    if num1!=num:
        if num1==1 or num1==5:
            root22.destroy()
        elif num1==2 or num1==5:
            root.destroy()
        
        elif num1==4 or num1==5:
            root11.destroy()
        elif num1==3 or num1==5:
            root95.destroy()
        num1=num
        mainflag=num
def getNestedFolderList(path):

    rv = [path]
    ls = os.listdir(path)
    if not ls:
        return rv

    for item in ls:
        itemPath = os.path.join(path,item)
        if os.path.isdir(itemPath):
            rv= rv+getNestedFolderList(itemPath)

    return rv

#Input:  A path to a folder
#Output: (folderName,path,mp3s) if the folder contains mp3s. Else None
def getFolderPlaylist(path):
    mp3s = []
    B=list()
    i=0
    ls = os.listdir(path)
    
    for item in ls:
        if item.count('mp3'):
            mp3s.append(item + "\n")
            #print item+"\n"
            
            B1= tk.Button(frame, text =item, command = partial(helloCallBack,path,item),bd=2,width=50,bg="yellow")
            B.append(B1)
            

            B[i].pack()
            i=i+1

    if len(mp3s) > 0:
        folderName = os.path.basename(path)
        return (folderName,path,mp3s)
    else:
        return None

#Input:  A path to a folder
#Output: List of all candidate playlists
def getFolderPlaylists(path):
    Tkinter1()
    rv = []
    print "BYE BYE"
    B=tk.Button(root, text ="KEYBOARD", command =partial(helloCallBack1,1),bd=1,width=50,bg="pink")#.grid(row=0,column=0)
    
    B.pack()
    B=tk.Button(root, text ="MUSIC", command =partial(helloCallBack1,2),bd=1,width=50,bg="pink")#.grid(row=0,column=1)
    B.pack()
    B=tk.Button(root, text ="MOUSE", command = partial(helloCallBack1,3),bd=1,width=50,bg="pink")#.grid(row=0,column=2)
    B.pack()
    B=tk.Button(root, text ="PPT", command =partial(helloCallBack1,4),bd=1,width=50,bg="pink")#.grid(row=0,column=3)
    B.pack()
    B=tk.Button(root, text ="EXIT", command = partial(helloCallBack1,5),bd=1,width=50,bg="pink")#.grid(row=0,column=3)
    B.pack()
    nestedFolderList = getNestedFolderList(path)
    for folderPath in nestedFolderList:
        folderPlaylist = getFolderPlaylist(folderPath)
        if folderPlaylist:
            rv.append(folderPlaylist)
            

    return rv


print "ROOT MAINLOOP"
#              MUSIC


#              KEYBOARD
root22=0
text=0

def submit1():
    global addr
    print text.get("1.0",END)
    f=[]
    f.append(1)
    
    f.append(text.get("1.0",END))
    f.append(1)
    
    s.sendto(pickle.dumps(f),addr)
    text.delete(1.0, END)
    ##ff[3]=1
def submit2():
    global addr
    f=[]
    f.append(0)
    
    f.append(text.get("1.0",END))
    f.append(1)
    
    s.sendto(pickle.dumps(f),addr)
    
    print text.get("1.0",END)
    #text.delete(1.0, END)
    #ff[3]==0


def keyboard():
    global root22
    global text
    root22 = tk.Tk()
    root22.title("KEYBOARD")
    
    root22.geometry('500x400+100+100')
    B=tk.Button(root22, text ="KEYBOARD", command = partial(helloCallBack1,1),bd=1,width=50,bg="pink")#.grid(row=0,column=0)
    B.pack()
    B=tk.Button(root22, text ="MUSIC", command = partial(helloCallBack1,2),bd=1,width=50,bg="pink")#.grid(row=0,column=1)
    B.pack()
    B=tk.Button(root22, text ="MOUSE", command = partial(helloCallBack1,3),bd=1,width=50,bg="pink")#.grid(row=0,column=2)
    B.pack()
    B=tk.Button(root22, text ="PPT", command = partial(helloCallBack1,4),bd=1,width=50,bg="pink")#.grid(row=0,column=3)
    B.pack()
    B=tk.Button(root22, text ="EXIT", command = partial(helloCallBack1,5),bd=1,width=50,bg="pink")#.grid(row=0,column=3)
    B.pack()
    w = Label(root22, text="ENTER TEXT", font=("Helvetica", 16) ,fg="RED")
    w.pack()
    canvas = tk.Canvas(root22, borderwidth=0, background="white",width=0,height=30)
    frame = tk.Frame(canvas, background="green")
    vsb = tk.Scrollbar(root22, orient="vertical", command=canvas.yview)
    canvas.configure(yscrollcommand=vsb.set)
    
    

    vsb.pack(side="right", fill="y")
    canvas.pack(side="right", fill="both", expand=True)
    canvas.create_window((0,0), window=frame, anchor="nw")

    frame.bind("<Configure>", lambda event, canvas=canvas: onFrameConfigure(canvas))
    frame=tk.Frame(root22,width=110,height=30)
    
    
    print "HAHA"
    
    #add a frame and put a text area into it
    frame.pack()
    textPad=tk.Frame(frame,width=110,height=30)
    text=tk.Text(textPad,height=30,width=110)
         
        # add a vertical scroll bar to the text area
    #scroll=tk.Scrollbar(textPad)
    #text.configure(yscrollcommand=scroll.set)
         
        #pack everything
    text.pack(side=LEFT)
    #scroll.pack(side=RIGHT,fill=Y)
    textPad.pack(side=TOP)
    B=tk.Button(canvas, text ="SUBMIT", command = submit1,bd=1,width=15,bg="red")#.grid(row=0,column=3)
    B.pack()
    B=tk.Button(canvas, text ="INSERT AT END", command =submit2,bd=1,width=15,bg="red")#.grid(row=0,column=3)
    B.pack()
    input = text.get("1.0",END)
    print input
    print "AMIT"


#              KEYBOARD

#              PPT
value1=0
i11=0
p11=0
root11=0
frame1=0

def onFrameConfigure1(canvas):
    '''Reset the scroll region to encompass the inner frame'''
    canvas.configure(scrollregion=canvas.bbox("all"))
def Tkinter2():
    global root11
    global frame1
    root11 = tk.Tk()
    root11.title("PRESENTATION")
    
    root11.geometry('700x400+100+100')

##SCROLLBAR
    canvas = tk.Canvas(root11, borderwidth=0, background="#ffffff")
    frame1 = tk.Frame(canvas, background="#ffffff",width=600)
    
    vsb = tk.Scrollbar(root11, orient="vertical", command=canvas.yview)
    canvas.configure(yscrollcommand=vsb.set)

    vsb.pack(side="right", fill="y")
    canvas.pack(side="right", fill="both", expand=True)
    canvas.create_window((4,4), window=frame1, anchor="nw")

    frame1.bind("<Configure>", lambda event, canvas=canvas: onFrameConfigure1(canvas))

###SCROLLBAAR




def getNestedFolderList1(path):

    rv = [path]
    ls = os.listdir(path)
    if not ls:
        return rv

    for item in ls:
        itemPath = os.path.join(path,item)
        if os.path.isdir(itemPath):
            rv= rv+getNestedFolderList1(itemPath)

    return rv

def helloCallBack2(path,item):
    
    ff=[]
    
    rr=path+"\\"+item
    print rr
    ff.append(1)
    ff.append(rr)
    ff.append(4)
    s.sendto(pickle.dumps(ff),addr)

def getFolderPlaylist1(path):
    mp3s = []
    B=list()
    i1=0
    ls = os.listdir(path)
    
    for item in ls:
        if item.count('ppt') or item.count('pptx'):
            mp3s.append(item + "\n")
            print item+"\n"
            
            B1= tk.Button(frame1, text =item, command = partial(helloCallBack2,path,item),bd=2,width=50,bg="orange")
            B.append(B1)
            

            B[i1].pack()
            i1=i1+1

    if len(mp3s) > 0:
        folderName = os.path.basename(path)
        return (folderName,path,mp3s)
    else:
        return None


def present(num):
    global addr
    ff=[]
    ff.append(0)
    ff.append(num)
    ff.append(4)
    s.sendto(pickle.dumps(ff),addr)

def getFolderPlaylists1(path):
    Tkinter2()
    rv = []
    
    B=tk.Button(root11, text ="KEYBOARD", command = partial(helloCallBack1,1),bd=1,width=50,bg="pink")#.grid(row=0,column=0)
    
    B.pack()
    B=tk.Button(root11, text ="MUSIC", command =partial(helloCallBack1,2),bd=1,width=50,bg="pink")#.grid(row=0,column=1)
    B.pack()
    B=tk.Button(root11, text ="MOUSE", command = partial(helloCallBack1,3),bd=1,width=50,bg="pink")#.grid(row=0,column=2)
    B.pack()
    B=tk.Button(root11, text ="PPT", command = partial(helloCallBack1,4),bd=1,width=50,bg="pink")#.grid(row=0,column=3)
    B.pack()
    B=tk.Button(root11, text ="EXIT", command = partial(helloCallBack1,5),bd=1,width=50,bg="pink")#.grid(row=0,column=3)
    B.pack()
    w = Label(root11, text="PRESENTATION", font=("Helvetica", 16) ,fg="BLUE")
    w.pack()
    w1=tk.Frame(root11,width=100,height=30,)
    w1.place(x='30',y='150')
    hh=tk.Button(w1,width=10,height=1,text="<<",bg="RED",command=partial(present,1))
    hh.grid(row=0,column=0,sticky='W')
    hh1=tk.Button(w1,width=10,height=1,text=">>",bg="BLUE",command=partial(present,2))
    hh1.grid(row=0,column=1,sticky='E')
    hh2=tk.Button(w1,width=10,height=1,text="EXIT",bg="RED",command=partial(present,3))
    hh2.grid(row=0,column=2,sticky='E')
    nestedFolderList = getNestedFolderList1(path)
    for folderPath in nestedFolderList:
        folderPlaylist = getFolderPlaylist1(folderPath)
        if folderPlaylist:
            rv.append(folderPlaylist)
            

    return rv


#              PPT
#           MOUSE
root95=0
left=0
right=0

def clicked():
    
    global addr
    global root95
    global left
    global right
    (x,y)=pyautogui.position()
    coor=[]
    x=(1366/1600.0)*x;
    y=(768/900.0)*y;
    coor.append(x)
    coor.append(y)
    coor.append(3)
    coor.append(3)
    print x," ",y
        
    s.sendto(pickle.dumps(coor),addr)
        
    (data,addr)=s.recvfrom(75)
        
    print "data"
    print "JAI MATA DI"
    right = False
    left = False
    root95.after(1000, clicked)
def rightclick(event):
    global root95
    global left
    global right
    global addr
    (x,y)=pyautogui.position()
    coor=[]
        #print coor
    coor.append(x)
    coor.append(y)
    coor.append(3)
    coor.append(2)
        
    print x," ",y
        
        

    s.sendto(pickle.dumps(coor),addr)
        
    (data,addr)=s.recvfrom(75)
        
    print "data"
    print "right click"
    right = True

def leftclick(event):
    global addr
    global root95
    global left
    global right
    (x,y)=pyautogui.position()
    coor=[]
    coor.append(x)
    coor.append(y)
    coor.append(3)
    coor.append(1)
        #print coor
    print x," ",y
        
        
    s.sendto(pickle.dumps(coor),addr)
    time.sleep(0.5)   
    (data,addr)=s.recvfrom(75)
        
    print "data"
    print "left Click"
    left = True
def mouse():
    
    global root95
    global left
    global right
    root95=tk.Tk()
    root95.geometry('500x400+100+100')
    left=False
    right=False
    frame=tk.Frame(root95,width=50,height=50)
    frame.place(x=0,y=0)
    B=tk.Button(frame, text ="KEYBOARD", command = partial(helloCallBack1,1),bd=1,width=50,bg="pink")#.grid(row=0,column=0)
    B.pack()
    B=tk.Button(frame, text ="MUSIC", command = partial(helloCallBack1,2),bd=1,width=50,bg="pink")#.grid(row=0,column=1)
    B.pack()
    B=tk.Button(frame, text ="MOUSE", command = partial(helloCallBack1,3),bd=1,width=50,bg="pink")#.grid(row=0,column=2)
    B.pack()
    B=tk.Button(frame, text ="PPT", command = partial(helloCallBack1,4),bd=1,width=50,bg="pink")#.grid(row=0,column=3)
    B.pack()
    B=tk.Button(frame, text ="EXIT", command = partial(helloCallBack1,5),bd=1,width=50,bg="pink")#.grid(row=0,column=3)
    B.pack()
    root95.bind('<Button-1>',leftclick)
    root95.bind('<Button-3>',rightclick)
    root95.after(10,clicked)

#           MOUSE


#(data111,addr)=s.recvfrom(1024)
print addr
# MAIN LOOP
while True:
    if mainflag==4:
        getFolderPlaylists1('A:\ppt')
#B.pack()
        root11.mainloop()
    elif mainflag==1:
        keyboard()
        root22.mainloop()
    elif mainflag==2:
        getFolderPlaylists('A:\WhatsAppAudio')
#B.pack()
        root.mainloop()
    elif mainflag==5:
        break
    elif mainflag==3:
        mouse()
        root95.mainloop()
        
print "WE ARE OUT"
