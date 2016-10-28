# iteazer
ITeazer

How to use?
===================

**1) Generate server-cylon.js** 

 - open `Ollie-competition/Server-Cylon-Generator` folder
	**1) You should edit (or create) ** `droids.txt` **text file**

	You should write there all droids that you want connect to the server. Supported droids are ollie and bb8. 
	Be **aware**, on deploying server chechks *connection to all droids* and it wouldn't start if at least one droid missed. 
	   + each line contains info about exectly one droid (document shouldn't contains empty lines)
	   + line starts with droid type (ollie/bb8)
	   + after type 1 or more spaces
	   + after spaces MAC address
	  
	  Example: `ollie   D8:E3:8C:77:D0:5D` 

	**2) execute java program (ServerCylonGenerator is main class)**

	**3) output: ** `cylon-server-generated.js`

<br>
**2) Copy/past generated ** `cylon-server-generated.js` ** to ** `NodeJs-Server/Cylon` ** folder** 
 
<br>
**3) Execute ** `cylon-server-generated.js` 

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
