function mouse():
	while True:

		data=s.recv(1024)
		data1=data.splits("\n")
		data2=data1[0].splits(" ")
		if data2[2]!=2:
			break
		if data2[3]==1 and data2[2]==2:
			pyautogui.moveTo(x,y,duration=0.5)
		elif data2[3]==2 and data2[2]==2:
			pyautogui.click()#LEFT CLICK
		elif data2[3]==3 and data2[2]==2:
			x1,y1=pyautogui.position()
			pyautogui.click(x,y,button='right')
	
	