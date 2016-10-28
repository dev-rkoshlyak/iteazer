How to use?
===================

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

