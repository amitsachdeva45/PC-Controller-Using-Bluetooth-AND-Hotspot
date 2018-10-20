import socket,pickle,pyautogui
import os, sys,vlc
import win32com.client
import threading
import subprocess as sp
s=socket.socket()
host=""
port=50048
addr=(host,port)
s.bind(addr)
s.listen(5)
conn,addr=s.accept()
print addr
xx=0
yy=0
flag2=0#for first saving old coordinate
musstart=""
pptstart=""
def mouse():
    while True:
        global xx
        global yy
        global flag2
        data=conn.recv(1024)
        data1=data.split("\n")
        data2=data1[0].split(" ")
        print data2
        if int(data2[2])!=2:
            flag2=0
            xx=0
            yy=0
            break
        if int(data2[3])==1 and int(data2[2])==2:
            x2,y2=pyautogui.position()
            x3=float(x2)
            y3=float(y2)
            if flag2==0:
                flag2=1
                print "FLAG2"
                xx=float(data2[0])
                yy=float(data2[1])
                r1=float(xx)*3.7
                r2=float(yy)*2.43
            else:
                r1=x3+(-xx+float(data2[0]))*3.7
                r2=y3+(-yy+float(data2[1]))*2.43
                xx=float(data2[0])
                yy=float(data2[1])
            if r1>float(1600):
                r1=float(1600)
            if r1<float(0):
                r1=float(0)
            if r2>float(1200):
                r2=float(1200)
            if r2<float(0):
                r2=float(0)
            print r1
            print r2
                
            pyautogui.moveTo(r1,r2,duration=0.1)
        elif int(data2[3])==2 and int(data2[2])==2:
            pyautogui.click()#LEFT CLICK
        elif int(data2[3])==3 and int(data2[2])==2:
            x1,y1=pyautogui.position()
            pyautogui.click(int(x1),int(y1),button='right')
            print "o"

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

def getFolderPlaylist1(path):
    global pptlist
    global muslist
    global musppt
    global pptstart
    global musstart
    mp3s = []
    #B=list()
    i1=0
    ls = os.listdir(path)
    print "YES"
    for item in ls:
        if (item.count('ppt') or item.count('pptx')) and musppt==1:
            mp3s.append(item + "\n")
            print item+"\n"
            pptlist.append(item)
            #B1= tk.Button(frame1, text =item, command = partial(helloCallBack,path,item),bd=2,width=50,bg="orange")
            #B.append(B1)
            

            #B[i1].pack()
            i1=i1+1
        elif (item.count('mp3')) and musppt==2:
            print "ENTERED"
            mp3s.append(item + "\n")
            #print item+"\n"
            muslist.append(item)
            #B1= tk.Button(frame1, text =item, command = partial(helloCallBack,path,item),bd=2,width=50,bg="orange")
            #B.append(B1)
            

            #B[i1].pack()
            i1=i1+1
        elif (item.count('mp3')) and musppt==3:
            #print "ENTERED"
            
            mp3s.append(item + "\n")
            if musstart=="":
                musstart=item
            else:
                musstart=musstart+","+item
            #print item+"\n"
            #muslist.append(item)
            #B1= tk.Button(frame1, text =item, command = partial(helloCallBack,path,item),bd=2,width=50,bg="orange")
            #B.append(B1)
            

            #B[i1].pack()
            i1=i1+1
        elif (item.count('ppt') or item.count('pptx')) and musppt==4:
            #print "ENTERED"
            mp3s.append(item + "\n")
            if pptstart=="":
                pptstart=item
            else:
                pptstart=pptstart+','+item
            
            
            #print item+"\n"
            #muslist.append(item)
            #B1= tk.Button(frame1, text =item, command = partial(helloCallBack,path,item),bd=2,width=50,bg="orange")
            #B.append(B1)
            

            #B[i1].pack()
            i1=i1+1

    if len(mp3s) > 0:
        folderName = os.path.basename(path)
        return (folderName,path,mp3s)
    else:
        return None
def getFolderPlaylists1(path,kk):
    global musppt
    rv = []
    musppt=kk
    nestedFolderList = getNestedFolderList1(path)
    for folderPath in nestedFolderList:
        folderPlaylist = getFolderPlaylist1(folderPath)
        if folderPlaylist:
            rv.append(folderPlaylist)
            

    return rv

def ppt():
    global pptlist
    global flag3
    global presenti
    getFolderPlaylists1('A:\ppt',1)
    while True:
        data=conn.recv(1024)
        k=data.split(" ")
        print k
        if int(k[2])<len(pptlist) and int(k[1])==1 and flag3==0 and presenti==0:
            flag3=1
            f=int(k[2])-1
            r=0
            h='A:\\ppt\\'+pptlist[f]
            print h
            app = win32com.client.Dispatch("PowerPoint.Application")
            presentation = app.Presentations.Open(h, ReadOnly=1)
            slide_count = len(presentation.Slides)
            presentation.SlideShowSettings.Run()
            presenti=presenti+1
        elif int(k[2])<len(pptlist) and int(k[1])==1 and presenti!=0:
            if flag3==1:
                del presentation
                app.Quit()
            
            flag3=1
            r=0
            f=int(k[2])-1
            h='A:\\ppt\\'+pptlist[f]
            print h
            app = win32com.client.Dispatch("PowerPoint.Application")
            presentation = app.Presentations.Open(h, ReadOnly=1)
            slide_count = len(presentation.Slides)
            presentation.SlideShowSettings.Run()
            presenti=presenti+1
        if int(k[1])==5:
            if flag3==1:
                del presentation
                app.Quit()
            
            break
        if int(k[0])==1 and int(k[1])==1 and flag3==1 and r<slide_count:
            presentation.SlideShowWindow.View.Next()
            r=r+1
        elif int(k[0])==2 and int(k[1])==1 and flag3==1 and r<slide_count:
            presentation.SlideShowWindow.View.Previous()
            r=r-1
        elif int(k[0])==3 and int(k[1])==1 and flag3==1:
            presentation.SlideShowWindow.View.Exit()
            del presentation
            app.Quit()
            flag3=0
            r=0
        
            
        if r==slide_count:
            del presentation
            app.Quit()
            flag3=0
            r=0
        if r<0:
            r=0
            
            
            
            
        
            
            
p1=0       
def music():
    global muslist
    getFolderPlaylists1('A:\WhatsAppAudio',2)
    i1=0
    value=0
    p1=0
    flag4=0
    kk=0
    while True:
        
        data2=conn.recv(1024)
        data3=data2.split(" ")
        print data3
        print len(muslist)
        if int(data3[0])==0 and i1==0 and int(data3[2])<len(muslist) and int(data3[1])==3:
            i1=i1+1
            kk=int(data3[2])-1
            m="A:\\WhatsAppAudio\\"+muslist[kk]
            print m
            p=vlc.MediaPlayer(m)
            p.play()
            p1=p
            value=r
            flag4=1
        elif int(data3[0])==0 and i1!=0 and int(data3[2])<len(muslist)  and int(data3[1])==3:
            p1.stop()
            
            kk=int(data3[2])-1
            m="A:\\WhatsAppAudio\\"+muslist[kk]
            print m
            p=vlc.MediaPlayer(m)
            p.play()
            p1=p
            value=r
            flag4=1
        elif int(data3[0])==1 and int(data3[1])==3 and flag4==1:
            p.play()
        elif int(data3[0])==2 and int(data3[1])==3 and flag4==1:
            p.pause()
        elif int(data3[0])==3 and int(data3[1])==3 and kk>0 and flag4==1:
            p1.stop()
            kk=kk+1
            m="A:\\WhatsAppAudio\\"+muslist[kk]
            print m
            p=vlc.MediaPlayer(m)
            p1=p
            p.play()
            value=r
        elif int(data3[0])==4 and int(data3[1])==3 and kk>0 and flag4==1:
            p1.stop()
            kk=kk-1
            m="A:\\WhatsAppAudio\\"+muslist[kk]
            print m
            p=vlc.MediaPlayer(m)
            p1=p
            p.play()
            value=r
        elif int(data3[0])==5 and int(data3[1])==3 and flag4==1:
            p1.stop()
        if int(data3[1])!=3:
            if flag4==1:
                p.stop()
            break
            
            
            
            
keybo=""           
def keyboard():
    global keybo
    z=""
    while True:
        data=conn.recv(1024)
    
        data1=data.split("!")
        print data1
        if data1[1]==str(5):
            keybo=""
            break
        if data1[0]==str(1000):
            z=z+keybo
            print z
            target = open('keyboard12.txt', 'w')
            
            target.write(z)
            target.close()
            keybo=""
            programName = "notepad.exe"
            fileName = "keyboard12.txt"
            sp.Popen([programName, fileName])
            
        else:
            keybo=keybo+" "+data1[0]
        
        
        
                 
                 
        
    

musppt=0
getFolderPlaylists1('A:\WhatsAppAudio',3)
getFolderPlaylists1('A:\ppt',4)
print conn.recv(1024)
conn.send(musstart+"\n")
#print musstart
conn.send(pptstart+"\n")
#print pptstart

while True:
    
    data2=conn.recv(1024)
    data3=data2.split("\n")
    
    r=int(data3[0])
    
    if r==2:
        
        print "MOUSE"
        mouse()
    elif r==1:
        pptlist=[]
        flag3=0
        presenti=0
        print "PPT"
        ppt()
    elif r==3:
        muslist=[]
        print "MUSIC"
        music()
    elif r==4:
        print "EXIT"
        break
    elif r==5:
        print "KEYBOARD"
        keyboard()

print "WE ARE OUT"
        
        
    

	
                
