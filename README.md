# iteazer
ITeazer

How to use?
===================

**1) Generate server-cylon.js** 

 - open Server-Cylon-Generator folder
 - edit `droids.txt`
 
 (You should write there all droids that you want connect to the server. Supported droids are ollie and bb8.)
 - execute java program 
 (ServerCylonGenerator is main class)

<br>
**2) Copy/past generated server-cylon.js to NodeJs-Server folder** 
 
 or you could locate it where ever you want and install (via npm install) few node.js modules:

 - noble
 - sphero
 - cylon
 - cylon-sphero
 - cylon-ble
 - cylon-sphero-ble


<br>
**3) Execute server-cylon.js**  

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
