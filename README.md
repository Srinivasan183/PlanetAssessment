# PlanetAssessment

EmployeeDetails is the class where you can run the funtions.

#prerequisites:
JDK - Java 8 should be configured in the environment variables.
Copy the InputFile1.txt and InputFile2.csv and paste it in "Documents/Assessment" folder.

#To Run Jar:
Copy and paste PlanetAssessment.Jar file anywhere in your windows machine.

Open cmd, GOTO jar file Directory and execute the below command.

java.exe -jar PlanetAssessment.jar

Once executed the above command, you would requested to provide 2 arguments.
1. Enter the File Format code in number (Integer) : 
2. Enter the sort element (either by firstName, LastName or Start date) :

First arg is file name, it should be either 1 or 2 or anything,  else the output will send a message
Second arg is the sort element, it should be either Firstname/lastname/start date, user can enter even name or date. if incorrect sort element will run with the default sort using firstname.

if the app running in a happy path, the out file "MyOutFile.txt" will be generated in the "Documents/Assessment" folder of your machine.
