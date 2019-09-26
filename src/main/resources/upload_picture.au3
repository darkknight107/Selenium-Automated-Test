#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.3.14.5
 Author:         myName

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here

ControlFocus("File Upload", "", "Edit1")
ControlSetText("File Upload", "", "Edit1", "C:\Users\Public\Pictures\Sample Pictures\Koala.jpg")
ControlClick("File Upload", "", "Button1")