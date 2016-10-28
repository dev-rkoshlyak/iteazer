# iteazer
ITeazer

How to use?
===================

**1) Generate cylon server** 

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**1. open `Ollie-competition/Server-Cylon-Generator` folder**
   
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**2. You should edit (or create)  `droids.txt` text file**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;You should write there all droids that you want connect to the server. Supported droids are ollie and bb8. 
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Be **aware**, on deploying server chechks *connection to all droids* and it wouldn't start if at least one droid missed. 
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ each line contains info about exectly one droid (document shouldn't contains empty lines)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ line starts with droid type (ollie/bb8)
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ after type 1 or more spaces
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ after spaces MAC address

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Example: `ollie   D8:E3:8C:77:D0:5D` 

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**3. execute java program (ServerCylonGenerator is main class)**
   
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**4. output:  `cylon-server-generated.js`**

<br>
**2) Copy/past generated `cylon-server-generated.js`  to  `NodeJs-Server/Cylon` folder** 
 **!** do not try to install node modules via `npm install`, because some modules a bit modified.
 
<br>
**3) Execute** `cylon-server-generated.js`

  - open terminal
  - run `sudo node server-cylon.js`
  - wait (about 10-20 sec) until you see in console:
  
     [Robot 1] - Starting devices.
  
     [Robot 1] - Working.

<br>
**4) Execute java web server (OllieCompetition)**

  - open OllieCompetition folder
  - edit `team.txt` file
  - execute OllieCompetition project
