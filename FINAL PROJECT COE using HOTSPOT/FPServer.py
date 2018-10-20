import os
import sys,time
import socket
import pickle
import vlc
import win32com.client
import Tkinter as tk
import threading
server_socket=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
server_socket.bind(("",5016))
server_socket.listen(5)




s, addr = server_socket.accept()
print "Conencted to - ",addr,"\n"
mainflag=2
#                    PPT
presenti=0
flagg1=0
slide_count=0
presentation=0
app=0
def present():
    global presenti
    global slide_count
    global flagg1
    global mainflag
    global app
    global presentation
    rr=0
    while True:
        (data1,addr)=s.recvfrom(1024)
        data=pickle.loads(data1)
        #print "BHENCHOD"
        if presenti==0 and data[0]==1 and data[2]==4:
            print "1"
            flagg1=1
            app = win32com.client.Dispatch("PowerPoint.Application")
            presentation = app.Presentations.Open(data[1], ReadOnly=1)
            slide_count = len(presentation.Slides)
            presentation.SlideShowSettings.Run()
            presenti=presenti+1
        elif presenti!=0 and data[0]==1 and data[2]==4:
            #del presentation
            #app.Quit()
            rr=0
            flagg1=1
            print "2"
            app = win32com.client.Dispatch("PowerPoint.Application")
            presentation = app.Presentations.Open(data[1], ReadOnly=1)
            slide_count = len(presentation.Slides)
            presentation.SlideShowSettings.Run()
            presenti=presenti+1
        if data[0]==0 and rr<slide_count and data[2]==4:
            print "NEXT"
            if data[1]==2:
                presentation.SlideShowWindow.View.Next()
            elif data[1]==1:
                presentation.SlideShowWindow.View.Previous()
            elif data[1]==3:
                presentation.SlideShowWindow.View.Exit()
                del presentation
                app.Quit()
                flagg1=0
            rr=rr+1
        if rr==slide_count:
            presentation.SlideShowWindow.View.Exit()
            #del presentation
            #app.Quit()
        if data[2]!=4:
            if flagg1==1:
                del presentation
                app.Quit()
            flagg1=0
            mainflag=data[2]
            break

print "PRESENTATION"
#                       PPT
#                         MUSIC
i1=0
value=0
p1=0
flagg=0
def music():
    global flagg
    flagg=0
    while True:
        global mainflag
        global i1
        global value
        global p1
        
        (data1,addr)=s.recvfrom(1024)
        pp=pickle.loads(data1)
        r=pp[1]
        print pp[1]
        if i1==0 and pp[2]==2:
            flagg=1
            value=r
            i1=i1+1
            p = vlc.MediaPlayer(r)
            p.play()
            p1=p
            print "i=0"
        elif pp[2]==2 and i1!=0:
            flagg=1
            p1.stop()
            print value
            p = vlc.MediaPlayer(r)
            if value==r:
                p.stop()
                print "AMIT"
            else:
                p.play()
            print r
            value=r
            p1=p
            print value
            print "HAHA"
        if pp[2]!=2:
            
            mainflag=pp[2]
            if flagg==1:
                
                p1.stop()
            flagg=0
            
            break
            
    

print mainflag
#                MUSIC
#               KEYBOARD
root=0
text1=""
root2=0
i=0
frame2=0
def quit1():
    root.destroy()
def keyboard():
    global root2
    global root
    global i
    global text1
    global mainflag
    global frame2
    #global END
    print "ENTER"
    frame=tk.Frame(root,width=400,height=400,relief='solid')
    frame.place(x=10,y=10)
    (f1,addr)=s.recvfrom(1024)
    ff=pickle.loads(f1)
	
    print ff[1]
    if ff[0]==0:
        if i==0:
           
            
            frame2=frame
            
            i=i+1
        else:
            frame2.destroy()
            frame2=frame
        text=tk.Label(frame,text=str(text1)+" "+str(ff[1]))
		
        #text1=text1+" "+ff[2]
        
        print "SACH"
    else:
        
        if i==0:
           
            
            frame2=frame
            
            i=i+1
        else:
            frame2.destroy()
            frame2=frame
        text=tk.Label(frame,text=str(ff[1]))
        text1=ff[1]
        print "DEVA"
    
    text.pack()
    text2=text
    if ff[2]!=1:
        mainflag=ff[2]
        B=tk.Button(frame,text ="QUIT",width=20,height=10,bd=1,bg="Red",command=quit1)
        B.place(x='0',y='0')
        return
        
    
    threading.Timer(6, keyboard).start()
#               kEYBOARD
while True:
    if mainflag==1:
        print "KEYBOARD"
        root=tk.Tk()
        root.title("OUTPUT OF KEYBOARD")
        root.geometry('400x300+100+100')
        keyboard()
        root.mainloop()
    elif mainflag==2:
        print "MUSIC"
        music()
    elif mainflag==5:
        print "EXIT"
        break
    elif mainflag==4:
        print "PPT"
        present()

print "WE ARE OUT"
        
                
            
            
            
        
